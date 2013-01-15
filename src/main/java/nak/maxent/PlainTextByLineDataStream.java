/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package nak.maxent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * This DataStream implementation will take care of reading a plain text file
 * and returning the Strings between each new line character, which is what
 * many Maxent applications need in order to create EventStreams.
 */
public class PlainTextByLineDataStream implements DataStream {
  BufferedReader dataReader;
  String next;

  public PlainTextByLineDataStream(Reader dataSource) {
    dataReader = new BufferedReader(dataSource);
    try {
      next = dataReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Object nextToken() {
    String current = next;
    try {
      next = dataReader.readLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return current;
  }

  public boolean hasNext() {
    return next != null;
  }
}

