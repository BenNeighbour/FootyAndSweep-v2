/*
 *   Copyright 2021 FootyAndSweep
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.footyandsweep.apicommonlibrary.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;

public class ProtoConverterUtils {

  private static final Gson gson = new GsonBuilder().serializeNulls().generateNonExecutableJson().create();
  private static final JsonFormat.Parser jsonParser = JsonFormat.parser().ignoringUnknownFields();
  private static final JsonFormat.Printer jsonPrinter = JsonFormat.printer().includingDefaultValueFields();

  public static <PojoType> PojoType convertToPojo(
      Class<PojoType> destPojoClass, Message sourceMessage) {
    String json = "";

    try {
      if (destPojoClass == null) {
        throw new IllegalArgumentException("No destination pojo class specified");
      } if (sourceMessage == null) {
        throw new IllegalArgumentException("No source message specified");
      }

      json = jsonPrinter.print(sourceMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return gson.fromJson(json, destPojoClass);
  }

  public static void convertToProto(Message.Builder destBuilder, Object sourcePojoBean) {
    try {
      if (destBuilder == null) {
        throw new IllegalArgumentException("No destination message builder specified");
      } if (sourcePojoBean == null) {
        throw new IllegalArgumentException("No source pojo specified");
      }

      String json = gson.toJson(sourcePojoBean);
      jsonParser.merge(json, destBuilder);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
