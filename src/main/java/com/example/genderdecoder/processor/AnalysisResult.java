/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.genderdecoder.processor;

//Instance used in TextProcessor

public class AnalysisResult {
	int countMasculine;
	int countFeminine;
	String genderMap;
        String jobTitle;
	
        //Constructor values (default)
	public AnalysisResult() {
		countMasculine = 0;
		countFeminine = 0;
		genderMap = new String();
                jobTitle = new String();
	}

        //Getters and setters
	public int getCountMasculine() {
		return countMasculine;
	}

	public void incrementCountMasculine() {
		this.countMasculine++;
	}

	public int getCountFeminine() {
		return countFeminine;
	}

	public void incrementCountFeminine() {
		this.countFeminine++;
	}

	public String getGenderMap() {
		return genderMap;
	}

        //Insert custom method for genderMap (stringBuffer appending on char when run on each interation)
	public void insertGenderMap(char code) {
		StringBuffer str= new StringBuffer(genderMap);
		str.append(code);
		genderMap = str.toString();
	}
        
	public String getJobTitle() {
		return jobTitle;
	}

        //Add job title to instance
	public void insertJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	
	
}
