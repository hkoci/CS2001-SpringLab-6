/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.genderdecoder.processor;
import opennlp.tools.stemmer.PorterStemmer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Component("textProcessor")
public class TextProcessor {
	String[] feminineCodedWords = {
	                        "agree",
	                        "affectionate",
	                        "child",
	                        "cheer",
	                        "collab",
	                        "commit",
	                        "communal",
	                        "compassion",
	                        "connect",
	                        "considerate",
	                        "cooper",
	                        "depend",
	                        "emotiona",
	                        "empath",
	                        "feel",
	                        "flatterable",
	                        "gentle",
	                        "honest",
	                        "interdependen",
	                        "interperson",
	                        "interdepend",
	                        "kind",
	                        "kinship",
	                        "loyal",
	                        "modesty",
	                        "nag",
	                        "nurtur",
	                        "pleasant",
	                        "polite",
	                        "quiet",
	                        "respon",
	                        "sensitiv",
	                        "submissive",
	                        "support",
	                        "sympath",
	                        "tender",
	                        "together",
	                        "trust",
	                        "understand",
	                        "warm",
	                        "whine",
	                        "enthusias",
	                        "inclusive",
	                        "yield",
	                        "share",
		};
	
	String[] masculineCodedWords = {
	                         "active",
	                         "adventurous",
	                         "aggress",
	                         "ambitio",
	                         "analy",
	                         "assert",
	                         "athlet",
	                         "autonom",
	                         "battle",
	                         "boast",
	                         "challeng",
	                         "champion",
	                         "compet",
	                         "confident",
	                         "courag",
	                         "decid",
	                         "decision",
	                         "decisive",
	                         "defend",
	                         "determin",
	                         "domina",
	                         "dominant",
	                         "driven",
	                         "fearless",
	                         "fight",
	                         "force",
	                         "greedy",
	                         "headstrong",
	                         "hierarch",
	                         "hostil",
	                         "impulsive",
	                         "independen",
	                         "individual",
	                         "intellect",
	                         "lead",
	                         "logic",
	                         "objective",
	                         "opinion",
	                         "outspoken",
	                         "persist",
	                         "principle",
	                         "reckless",
	                         "selfreli",
	                         "selfsuffici",
	                         "selfconfid",
	                         "selfrelian",
	                         "stubborn",
	                         "superior",
	                         "unreasonab"
		};
	
	
	HashSet<String> masculineCodedSet;
	HashSet<String> feminineCodedSet;
	PorterStemmer porterStemmer;
	
	public TextProcessor() {
		masculineCodedSet = new HashSet<String>();
		feminineCodedSet  = new HashSet<String>();
		
		porterStemmer = new PorterStemmer();
		for (String word : masculineCodedWords) {
			String stem = this.porterStemmer.stem(word);
			masculineCodedSet.add(stem);
		}
		for (String word : feminineCodedWords) {
			String stem = porterStemmer.stem(word);
			feminineCodedSet.add(stem);
		}
				
	}

	public AnalysisResult analyze(String text) {
	   AnalysisResult result = new AnalysisResult();
           
            //Handle job title
            //Remove all characters before #

            //Get index char in String
            int hashIndex = text.indexOf('*');

            //Attempt to get substring using characters after hash char
            String jobTitle = text.substring(hashIndex+1);

            //Base case: check if there is any job title first!
            if(jobTitle.equals(jobTitle) || jobTitle.equals("")){
                result.insertJobTitle("N/A"); //Insert N/A to instance
            }

            //Insert job title to AnalysisResult instance
            result.insertJobTitle(jobTitle);
            
	   List<String> tokenizedText = Arrays.asList(text.toLowerCase().split(" "));
		
		for (String token : tokenizedText) {
		    String stem = porterStemmer.stem(token.replace("-", ""));
		    if (masculineCodedSet.contains(stem)) {
		    	result.incrementCountMasculine();
		     	result.insertGenderMap('m');
		    } 	
		    else if(feminineCodedSet.contains(stem)) {
		    	result.incrementCountFeminine();
		    	result.insertGenderMap('f');
		    }else {
		    	result.insertGenderMap('n');
		    }
		}

		return result;
				
	}
}
