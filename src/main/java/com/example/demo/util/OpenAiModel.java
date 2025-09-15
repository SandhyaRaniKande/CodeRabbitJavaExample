package com.example.demo.util;

public class OpenAiModel implements GenAiModel{
    @Override
    public void print(String name)
    {
        System.out.println("This  Model is "+name);
    }
    public void addition (int i, int j)
    {
        try{
            System.out.println("This addition value "+(i+j));
        }
        catch (Exception e)
        {

        }
    }
}
