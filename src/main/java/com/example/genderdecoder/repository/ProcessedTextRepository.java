/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.genderdecoder.repository;

import com.example.genderdecoder.model.ProcessedText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedTextRepository extends JpaRepository<ProcessedText, Long>{
    ProcessedText findById(long id);
}






