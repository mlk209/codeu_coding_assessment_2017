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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



final class MyJSON implements JSON {
  public Map<String,Object> map_obj = new HashMap<String,Object>();
  public Map<String,String> map_str = new HashMap<String,String>();

  
  @Override
  public JSON getObject(String name) {
    // TODO: implement this
    return (JSON) map_obj.get(name);
    
  
  }

  @Override
  public JSON setObject(String name, JSON value) {
    // TODO: implement this
    map_obj.put(name,value);
    return this;
  }

  @Override
  public String getString(String name) {
    // TODO: implement this
    return (String) map_str.get(name);
    
   
  }

  @Override
  public JSON setString(String name, String value) {
    // TODO: implement this
    map_str.put(name,value);
    return this;
  }

  @Override
  public void getObjects(Collection<String> names) {
    // TODO: implement this
    Set<String>keys = map_obj.keySet();
    for(String key: keys){
      names.add(key);
    }
  }

  @Override
  public void getStrings(Collection<String> names) {
    // TODO: implement this
    Set<String>keys = map_str.keySet();
    for(String key:keys){
      names.add(key);
    }
  }

}
