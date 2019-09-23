package com.company;


import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

//Choose all hospitals from Texas (or any other state as you choose) that are government owned,
//meaningful use of EHRs enabled, and same as or above the national average of patient expierence,
//then create a new tab-delimited file. Test the runtime speed of this program wrriten in C#.


public class HospitalDataProcessor {
    FileReader fr;
    BufferedReader br;
    ArrayList<Hospital> hospitals = new ArrayList<>();
    ArrayList<Hospital> searchResults = new ArrayList<>();

    HospitalDataProcessor(String dataPath) throws IOException {
        fr = new FileReader(dataPath);
        br = new BufferedReader(fr);
        loadData();
    }

    void loadData() throws IOException {
        String dataLine = "";
        String [] line;
        Hospital hospital;
        br.readLine(); //skip the first header line
        while((dataLine=br.readLine())!=null){
            line = dataLine.split("\t");
            hospital = new Hospital();
            hospital.setProviderID(Integer.parseInt(line[0]));
            hospital.setHospitalName(line[1]);
            hospital.setAddress(line[2]);
            hospital.setCity(line[3]);
            hospital.setState(line[4]);
            hospital.setZIPCode(Integer.parseInt(line[5]));
            hospital.setCountyName(line[6]);
            hospital.setPhoneNumber(Long.parseLong(line[7]));
            hospital.setHospitalType(line[8]);
            hospital.setHospitalOwnership(line[9]);
            hospital.setEmergencyServices(line[10]);
            hospital.setMeetsCriteriaForMeaningfulUseOfEHRs(line[11]);
            hospital.setHospitalOverallRating(line[12]);
            hospital.setHospitalOverallRatingFootnote(line[13]);
            hospital.setMortalityNationalComparison(line[14]);
            hospital.setMortalityNationalComparisonFootnote(line[15]);
            hospital.setSafetyOfCareNationalComparison(line[16]);
            hospital.setSafetyOfCareNationalComparisonFootnote(line[17]);
            hospital.setReadmissionNationalComparison(line[18]);
            hospital.setReadmissionNationalComparisonFootnote(line[19]);
            hospital.setPatientExperienceNationalComparison(line[20]);
            hospital.setPatientExperienceNationalComparisonFootnote(line[21]);
            hospital.setEffectivenessOfCareNationalComparison(line[22]);
            hospital.setEffectivenessOfCareNationalComparisonFootnote(line[23]);
            hospital.setTimelinessOfCareNationalComparison(line[24]);
            hospital.setTimelinessOfCareNationalComparisonFootnote(line[25]);
            hospital.setEfficientUseOfMedicalImagingNationalComparison(line[26]);
            hospital.setEfficientUseOfMedicalImagingNationalComparisonFootnote(line[27]);
            hospital.setLocation(line[28]);

            hospitals.add(hospital);
        }
        br.close();
        fr.close();
    }

    void displayAndWriteSearchResults(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("Provider ID\tHospital Name\tAddress\tCity\tState\tZIP Code\tCounty Name\tPhone Number\tHospital Type\tHospital Ownership\tEmergency Services\tMeets criteria for meaningful use of EHRs\tHospital overall rating\tHospital overall rating footnote\tMortality national comparison\tMortality national comparison footnote\tSafety of care national comparison\tSafety of care national comparison footnote\tReadmission national comparison\tReadmission national comparison footnote\tPatient experience national comparison\tPatient experience national comparison footnote\tEffectiveness of care national comparison\tEffectiveness of care national comparison footnote\tTimeliness of care national comparison\tTimeliness of care national comparison footnote\tEfficient use of medical imaging national comparison\tEfficient use of medical imaging national comparison footnote\tLocation\n");

        Hospital[] hospitalsArray = hospitals.toArray(new Hospital[0]);

        Hospital[] searchResultsArray =Arrays.stream(hospitalsArray).filter(o->(o.getState().equals("TX")&&(o.getHospitalOwnership().toLowerCase().contains("government"))&&(o.getMeetsCriteriaForMeaningfulUseOfEHRs().toLowerCase().equals("true"))&&((o.getPatientExperienceNationalComparison().toLowerCase().contains("same"))||(o.getPatientExperienceNationalComparison().toLowerCase().contains("above"))))).collect(Collectors.toList()).toArray(new Hospital[0]);

        for (var line :
                searchResultsArray) {
            fw.write(line.getProviderID()+"\t"+line.getHospitalName()+"\t"+line.getAddress()+"\t"+line.getCity()+"\t"+line.getState()+"\t"+line.getZIPCode()+"\t"+line.getCountyName()+"\t"+line.getPhoneNumber()+"\t"+line.getHospitalType()+"\t"+line.getHospitalOwnership()+"\t"+line.getEmergencyServices()+"\t"+line.getMeetsCriteriaForMeaningfulUseOfEHRs()+"\t"+line.getHospitalOverallRating()+"\t"+line.getHospitalOverallRatingFootnote()+"\t"+line.getMortalityNationalComparison()+"\t"+line.getMortalityNationalComparisonFootnote()+"\t"+line.getSafetyOfCareNationalComparison()+"\t"+line.getSafetyOfCareNationalComparisonFootnote()+"\t"+line.getReadmissionNationalComparison()+"\t"+line.getReadmissionNationalComparisonFootnote()+"\t"+line.getPatientExperienceNationalComparison()+"\t"+line.getPatientExperienceNationalComparisonFootnote()+"\t"+line.getEffectivenessOfCareNationalComparison()+"\t"+line.getEffectivenessOfCareNationalComparisonFootnote()+"\t"+line.getTimelinessOfCareNationalComparison()+"\t"+line.getTimelinessOfCareNationalComparisonFootnote()+"\t"+line.getEfficientUseOfMedicalImagingNationalComparison()+"\t"+line.getEfficientUseOfMedicalImagingNationalComparisonFootnote()+"\t"+line.getLocation()+"\n");
        }
        fw.close();
    }

}
