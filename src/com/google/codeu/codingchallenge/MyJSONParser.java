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

import java.io.IOException;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

final class MyJSONParser implements JSONParser {
  
  @Override
  public JSON parse(String in) throws IOException {
    // TODO: implement this
	boolean isKey = true;
	String a_str = "";
    Stack <String> key = new Stack<String>();
    Stack <String> value = new Stack<String>();
    Map<String,Object> my_map= new HashMap<String,Object>();
    Map<String,Object> main_map = new HashMap<String,Object>();
    MyJSON myjson = new MyJSON();
    MyJSON tempjson = new MyJSON();
    int braketCount = 0;
    int parseWord = 0;

    //traverses through each character of the string 
    for ( int i = 0; i< in.length();i++){
    	if(in.charAt(i) == '{'){
    		//keeps count of the number of brackets in the string
    		if(braketCount == 1){
    			//isKey should keep track of the each "key" turns false when it reaches a {
    			isKey = !isKey;
    		}
    		//if there is a { present, add 1 
    		braketCount ++;
    	}
    	else if (in.charAt(i) == '}'){
    		//you have reached the end of all sets of brackets
    		if (braketCount == 1){
    			//there still exists a key 
    			if (!key.empty()){
    				//add the remaining key with its values to myjson 
    				myjson.setString(key.pop(),value.pop());
    			}
    		//you have reached at least one valid key-value pair
    		}
    		else{
    			//there is a nested key value pair where the value of the key is another dictionary
    			if (!key.empty()){
    			tempjson.setString(key.pop(),value.pop());
    			myjson.setObject(key.pop(), tempjson);
    			}
    		}
    		//signals the end of a braket 
    		braketCount --;
    		
    	}
    	else if (in.charAt(i) == '\"'){
    		//signals the end of a set of quotations and resets to 1 
    		if (parseWord == 0){
    			parseWord = 1;
    		}
    		else { 
    			parseWord = 0;
    			//it has reached a key, add to the key stack 
    			if (isKey){
    				//trim removes the trailing and leading spaces 
    				String noSpace = a_str.trim();
    				key.push(noSpace);
    			}
    			// since it is not a key, it must be a value 
    			else{
    				String noSpace = a_str.trim();
    				value.push(noSpace);
    			}
    		}
    		//resets the word that could either be a key or a value
    		a_str="";
    	}
    	else if (in.charAt(i) == ':'){
    		//this signals that the next term is not a key 
    		isKey = !isKey;
    	}
    	else if (in.charAt(i) == ','){
    		if (!key.empty()){
    			// signals the it has reached a key-value pair adds to tempjson 
    			tempjson.setString(key.pop(),value.pop());
    		}
    		isKey = !isKey;
    	}
    	else{
    			//keeps track of each word the parser encounters 
    			a_str += in.charAt(i);
    		}
    	

    	}
    	return myjson;
	}	
}
  

