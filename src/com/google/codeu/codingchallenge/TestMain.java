// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.util.Collection;
import java.util.HashSet;

final class TestMain {

  public static void main(String[] args) {

    final Tester tests = new Tester();

    tests.add("Empty Object", new Test() {
      @Override
      public void run(JSONFactory factory) throws Exception {
        final JSONParser parser = factory.parser();
        final JSON obj = parser.parse("{ }");

        final Collection<String> strings = new HashSet<>();
        obj.getStrings(strings);

        Asserts.isEqual(strings.size(), 0);

        final Collection<String> objects = new HashSet<>();
        obj.getObjects(objects);

        Asserts.isEqual(objects.size(), 0);
      }
    });

    tests.add("String Value", new Test() {
      @Override
      public void run(JSONFactory factory) throws Exception {
        final JSONParser parser = factory.parser();
        //fails string 
        //final JSON obj = parser. parse ("{ \"name\":{\"first\":\"sam\", \"last\":\"doe\"} }");
        final JSON obj = parser.parse("{ \"name\":\"sam doe\" }");

        //Asserts.isEqual("{first:sam:last:doe}", obj.getString("name"));
        Asserts.isEqual("sam doe", obj.getString("name"));
     }
    });

    tests.add("Object Value", new Test() {
      @Override
      public void run(JSONFactory factory) throws Exception {

        final JSONParser parser = factory.parser();
        // fails object
        //final JSON obj = parser.parse("{ \"name\":{\"first\":\"sam\" \"last\":\"doe\" } }");
        final JSON obj = parser.parse("{ \"name\":{\"first\":\"sam\", \"last\":\"doe\" } }");

        final JSON nameObj = obj.getObject("name");

        Asserts.isNotNull(nameObj);
        Asserts.isEqual("sam", nameObj.getString("first"));
        Asserts.isEqual("doe", nameObj.getString("last"));
      }
    });
    //fails this test since the tempjson is not cleared after it is added to myjson 
    tests.add("Nested", new Test() {
      @Override
      public void run(JSONFactory factory) throws Exception {

        final JSONParser parser = factory.parser();
        // fails object
        
        final JSON obj = parser.parse("{ \"name\":{\"first\":\"sam\", \"last\":\"doe\" },{\"school\":{\"elementary\":\"first\",\"middle\":\"sixth\"} }");

        final JSON nameObj = obj.getObject("name");
        final JSON nameObj2 = obj.getObject("school");

        Asserts.isNotNull(nameObj);
        Asserts.isNotNull(nameObj2);
        Asserts.isEqual("sam", nameObj.getString("first"));
        Asserts.isEqual("doe", nameObj.getString("last"));
        Asserts.isEqual("first", nameObj.getString("elementary"));
        Asserts.isEqual("sixth", nameObj.getString("middle"));
      }
    });
     tests.add("Two Key Value", new Test() {
      @Override
      public void run(JSONFactory factory) throws Exception {
        final JSONParser parser = factory.parser();

        final JSON obj = parser.parse("{ \"name\":\"sam doe\"} ,{\"grade\":\"eleven\"}");

        Asserts.isEqual("sam doe", obj.getString("name"));
        Asserts.isEqual("eleven", obj.getString("grade"));
     }
    });

    tests.run(new JSONFactory(){
      @Override
      public JSONParser parser() {
        return new MyJSONParser();
      }

      @Override
      public JSON object() {
        return new MyJSON();
      }
    });
  }
}
