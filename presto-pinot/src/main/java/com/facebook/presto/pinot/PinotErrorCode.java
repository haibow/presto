/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.pinot;

import com.facebook.presto.spi.ErrorCode;
import com.facebook.presto.spi.ErrorCodeSupplier;
import com.facebook.presto.spi.ErrorType;

import static com.facebook.presto.spi.ErrorType.EXTERNAL;
import static com.facebook.presto.spi.ErrorType.INTERNAL_ERROR;

public enum PinotErrorCode
        implements ErrorCodeSupplier
{
    PINOT_UNSUPPORTED_COLUMN_TYPE(0, EXTERNAL),
    PINOT_FAILURE_GETTING_TABLE(1, EXTERNAL),
    PINOT_FAILURE_GETTING_SCHEMA(2, EXTERNAL),
    PINOT_FAILURE_QUERYING_DATA(3, EXTERNAL),
    PINOT_FAILURE_INITIATING_RESOURCES(4, INTERNAL_ERROR);

    /**
     * Connectors can use error codes starting at the range 0x0100_0000
     * See https://github.com/prestodb/presto/wiki/Error-Codes
     *
     * @see com.facebook.presto.spi.StandardErrorCode
     */

    private final ErrorCode errorCode;

    PinotErrorCode(int code, ErrorType type)
    {
        errorCode = new ErrorCode(code + 0x0106_0000, name(), type);
    }

    @Override
    public ErrorCode toErrorCode()
    {
        return errorCode;
    }
}