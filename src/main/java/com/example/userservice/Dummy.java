package com.example.userservice;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Dummy {
    public static void main(String[] args) {
        MultiValueMap<String, String> map=new LinkedMultiValueMap<>();
        map.put("name",new ArrayList<>());

        MultiValueMapAdapter<String, String> multiValueMapAdapter=new MultiValueMapAdapter<>(new HashMap<>());
        multiValueMapAdapter.add("Key","1234");
        multiValueMapAdapter.add("Key","7777");

        System.out.println(multiValueMapAdapter);
    }

}
