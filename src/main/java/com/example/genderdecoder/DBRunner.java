/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.genderdecoder;

import com.example.genderdecoder.model.ProcessedText;
import com.example.genderdecoder.processor.AnalysisResult;
import com.example.genderdecoder.processor.TextProcessor;
import com.example.genderdecoder.repository.ProcessedTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBRunner implements CommandLineRunner{
	@Autowired 
	private ProcessedTextRepository sampleRepository;
	
	@Autowired
    TextProcessor textProcessor;
	
	@Override
    public void run(String... args) throws Exception {
        //Remove previous entities
        sampleRepository.deleteAll();
        
        //Sample Text
        String sampleText1 = "Looking for a cheerful Java developer";
        String sampleTitle1 = "Java Developer Title";
        
        //Run the gender analyser
        AnalysisResult result1 = textProcessor.analyze(sampleText1);
        
        //Save record to mySQL database
        sampleRepository.save(new ProcessedText(sampleText1, sampleTitle1, result1.getCountMasculine(), result1.getCountFeminine(), result1.getGenderMap()));
  
    }

}
