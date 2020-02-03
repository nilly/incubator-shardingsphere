/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.shardingscaling.postgresql.wal.decode;

import org.apache.shardingsphere.shardingscaling.core.exception.SyncTaskExecuteException;
import org.apache.shardingsphere.shardingscaling.postgresql.wal.event.AbstractRowEvent;
import org.apache.shardingsphere.shardingscaling.postgresql.wal.event.AbstractWalEvent;
import org.apache.shardingsphere.shardingscaling.postgresql.wal.event.DeleteRowEvent;
import org.apache.shardingsphere.shardingscaling.postgresql.wal.event.PlaceholderEvent;
import org.apache.shardingsphere.shardingscaling.postgresql.wal.event.UpdateRowEvent;
import org.apache.shardingsphere.shardingscaling.postgresql.wal.event.WriteRowEvent;
import org.postgresql.replication.LogSequenceNumber;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Test decoding plugin.
 *
 * @author avalon566
 */
public final class TestDecodingPlugin implements DecodingPlugin {
    
    @Override
    public AbstractWalEvent decode(final ByteBuffer data, final LogSequenceNumber logSequenceNumber) {
        AbstractWalEvent result;
        String eventType = readEventType(data);
        switch (eventType) {
            case "table":
                result = readTableEvent(data);
                break;
            default:
                result = new PlaceholderEvent();
                break;
        }
        result.setLogSequenceNumber(logSequenceNumber);
        return result;
    }
    
    private String readEventType(final ByteBuffer data) {
        return readNextSegment(data);
    }
    
    private AbstractRowEvent readTableEvent(final ByteBuffer data) {
        AbstractRowEvent result;
        String tableName = readTableName(data);
        String rowEventType = readRowEventType(data);
        switch (rowEventType) {
            case "INSERT":
                result = readWriteRowEvent(data);
                break;
            case "UPDATE":
                result = readUpdateRowEvent(data);
                break;
            case "DELETE":
                result = readDeleteRowEvent(data);
                break;
            default:
                throw new SyncTaskExecuteException("");
        }
        String[] tableMetadata = tableName.split("\\.");
        //result.setSchemaName(tableMetadata[0]);
        result.setSchemaName("test");
        result.setTableName(tableMetadata[1].substring(0, tableMetadata[1].length() - 1));
        return result;
    }
    
    private AbstractRowEvent readWriteRowEvent(final ByteBuffer data) {
        WriteRowEvent writeRowEvent = new WriteRowEvent();
        List<Object> afterColumns = new LinkedList<>();
        
        while (data.hasRemaining()) {
            afterColumns.add(readColumn(data));
        }
        writeRowEvent.setAfterRows(afterColumns);
        return writeRowEvent;
    }
    
    private AbstractRowEvent readUpdateRowEvent(final ByteBuffer data) {
        UpdateRowEvent updateRowEvent = new UpdateRowEvent();
        List<Object> afterColumns = new LinkedList<>();
        
        while (data.hasRemaining()) {
            afterColumns.add(readColumn(data));
        }
        updateRowEvent.setAfterRows(afterColumns);
        return updateRowEvent;
    }
    
    private AbstractRowEvent readDeleteRowEvent(final ByteBuffer data) {
        DeleteRowEvent deleteRowEvent = new DeleteRowEvent();
        List<Object> afterColumns = new LinkedList<>();
        
        while (data.hasRemaining()) {
            afterColumns.add(readColumn(data));
        }
        deleteRowEvent.setPrimaryKeyRows(afterColumns);
        return deleteRowEvent;
    }
    
    private String readTableName(final ByteBuffer data) {
        return readNextSegment(data);
    }
    
    private String readRowEventType(final ByteBuffer data) {
        String result = readNextSegment(data);
        // remove ":"
        return result.substring(0, result.length() - 1);
    }
    
    private Object readColumn(final ByteBuffer data) {
        String columnName = readColumnName(data);
        String columnType = readColumnType(data);
        // remove ":"
        data.get();
        return readColumnData(data, columnType);
    }
    
    private String readColumnName(final ByteBuffer data) {
        StringBuilder eventType = new StringBuilder();
        while (data.hasRemaining()) {
            char c = (char) data.get();
            if ('[' == c) {
                return eventType.toString();
            }
            eventType.append(c);
        }
        return eventType.toString();
    }
    
    private String readColumnType(final ByteBuffer data) {
        StringBuilder eventType = new StringBuilder();
        while (data.hasRemaining()) {
            char c = (char) data.get();
            if (']' == c) {
                return eventType.toString();
            }
            eventType.append(c);
        }
        return eventType.toString();
    }
    
    // CHECKSTYLE:OFF
    private Object readColumnData(final ByteBuffer data, final String columnType) {
        if (columnType.startsWith("numeric")) {
            return new BigDecimal(readNextSegment(data));
        }
        if (columnType.startsWith("bit") || columnType.startsWith("bit varying")) {
            return readNextSegment(data);
        }
        try {
            switch (columnType) {
                case "smallint":
                    return Short.parseShort(readNextSegment(data));
                case "integer":
                    return Integer.parseInt(readNextSegment(data));
                case "bigint":
                    return Long.parseLong(readNextSegment(data));
                case "real":
                    return Float.parseFloat(readNextSegment(data));
                case "double precision":
                    return Double.parseDouble(readNextSegment(data));
                case "boolean":
                    return Boolean.parseBoolean(readNextSegment(data));
                case "time without time zone":
                    return Time.valueOf(readNextString(data).substring(0, 7));
                case "date":
                    return Date.valueOf(readNextString(data));
                case "timestamp without time zone":
                    return Timestamp.valueOf(readNextString(data));
                case "bytea":
                    return decodeHex(readNextString(data).substring(2).toCharArray());
                default:
                    return readNextString(data);
            }
        } catch (Exception ex) {
            System.out.println(new String(data.array()));
            throw ex;
        }
    }
    
    // CHECKSTYLE:ON
    
    private String readNextSegment(final ByteBuffer data) {
        StringBuilder eventType = new StringBuilder();
        while (data.hasRemaining()) {
            char c = (char) data.get();
            if (' ' == c) {
                return eventType.toString();
            }
            eventType.append(c);
        }
        return eventType.toString();
    }
    
    private String readNextString(final ByteBuffer data) {
        StringBuilder result = new StringBuilder();
        // remove start "'"
        data.get();
        while (data.hasRemaining()) {
            char c = (char) data.get();
            if ('\'' == c) {
                if (!data.hasRemaining()) {
                    return result.toString();
                }
                char c2 = (char) data.get();
                // CHECKSTYLE:OFF
                if ('\'' == c2) {
                    // ignored
                } else if (' ' == c2) {
                    return result.toString();
                } else {
                    throw new SyncTaskExecuteException("Read character varying data unexpected exception");
                }
                // CHECKSTYLE:ON
            }
            result.append(c);
        }
        return result.toString();
    }
    
    private static byte[] decodeHex(final char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("未知的字符");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }
    
    private static int toDigit(final char ch, final int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("非法16进制字符 " + ch
                    + " 在索引 " + index);
        }
        return digit;
    }
}