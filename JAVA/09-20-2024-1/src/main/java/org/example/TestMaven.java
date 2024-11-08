package org.example;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestMaven {
    public static void main(String[] args) {

        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi");
        fruits.forEach(System.out::println);

        List<String> reverseFruits = Lists.reverse(fruits);
        reverseFruits.forEach(System.out::println);

        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("key", "firstValue");
        map.put("key", "secondValue");
        System.out.println(map);


        Properties prop = new Properties();
        try (InputStream input = TestMaven.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(prop.get("props.local.hello"));
        System.out.println(prop.get("props.mvn.hello"));
    }
}
