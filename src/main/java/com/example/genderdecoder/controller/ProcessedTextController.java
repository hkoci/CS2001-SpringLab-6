/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.genderdecoder.controller;

import com.example.genderdecoder.model.ProcessedText;
import com.example.genderdecoder.repository.ProcessedTextRepository;
import com.example.genderdecoder.processor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProcessedTextController {

    @Autowired
    ProcessedTextRepository textRepository;
    
    @Autowired
    TextProcessor textProcessor;
    
    //Analyze a Job Text
    //Return analysis - without saving to DB!
    
    //Simple return as AnalysisResult instance.
    @GetMapping("/analyze/{submission}")
    public AnalysisResult getAnalysis(@PathVariable(value = "submission") String submission) {
        return textProcessor.analyze(submission);
    }

    // Get All Job Texts
    @GetMapping("/texts")
    public List<ProcessedText> getAllTexts() {
        return textRepository.findAll();
    }

    // Create a new Job Text
    // When specifying a job title with job text, include * for this to be separated
    @PostMapping("/texts/{job}") 
    public ProcessedText createText(@PathVariable(value = "job") String jobText) {
        //Create a new AnalysisResult instance
        AnalysisResult analysis = textProcessor.analyze(jobText);

        //Now store this record to the database
        textRepository.save(new ProcessedText(jobText, analysis.getJobTitle(), analysis.getCountMasculine(), analysis.getCountFeminine(), analysis.getGenderMap()));
        
        //Return this information back
        return new ProcessedText(jobText, analysis.getJobTitle(), analysis.getCountMasculine(), analysis.getCountFeminine(), analysis.getGenderMap());
    }

    // Get a Single Job Text
    @GetMapping("/texts/{id}")
    public ProcessedText getNoteById(@PathVariable(value = "id") Long textId) {
        return textRepository.findById(textId)
                .orElseThrow(RuntimeException::new);
    }

    // Delete a Job Text
    @DeleteMapping("/texts/{id}")
    public String remove(@PathVariable(value = "id") Long textId) {
        if (textRepository.existsById(textId)) {
            textRepository.deleteById(textId);
            return "ID: " + textId + " successfully deleted";
        }
        else{
            //ID Does not exist!
            return "ID: " + textId + " does not exist";
        }
        
    }
    
}
