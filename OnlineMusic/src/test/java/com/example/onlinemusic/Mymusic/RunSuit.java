package com.example.onlinemusic.Mymusic;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({LoginTest.class,RegistTest.class,MusicListTest.class,FileUploadTest.class})
public class RunSuit {
}
