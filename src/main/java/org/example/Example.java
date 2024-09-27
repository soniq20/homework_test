package org.example;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Example{
    public static void main(String[] args)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json ="{\"name\":\"John\", \"age\":30}";
        MyObject obj = mapper.readValue(json, MyObject.class);
        System.out.println(obj.getName());
        System.out.println(obj.getAge());

    }
}
