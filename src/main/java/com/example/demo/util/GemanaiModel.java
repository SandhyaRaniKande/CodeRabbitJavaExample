package com.example.demo.util;

public class GemanaiModel implements GenAiModel{
    @Override
    public void print(String name)
    {
        System.out.println("This is open model: "+name);
    }
    public void division (int i, int j)
    {
        try{
            System.out.println("This division value "+(i/j));
        }
        catch (Exception e)
        {

        }
    }
}
