package com.example.demo.service;

import com.example.demo.util.GemanaiModel;
import com.example.demo.util.GenAiModel;
import com.example.demo.util.OpenAiModel;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private  GenAiModel model;
    private String name;
    private OpenAiModel   openAiModel;
    private  GemanaiModel gemanaiModel;
    public ModelService()
    {

    }
    public ModelService(OpenAiModel openAiModel,GemanaiModel gemanaiModel)
    {
        this.openAiModel=openAiModel;
        this.gemanaiModel=gemanaiModel;
    }
    public  void getModel(String name)
    {
        try {
            this.name=name;
            if ("OpenAi".equalsIgnoreCase(name)) {
                this.model = openAiModel;
            } else {
                this.model = gemanaiModel;
            }
        }
        catch (Exception e)
        {

        }
    }
    public void printModel()
    {
        model.print(name);
    }


}
