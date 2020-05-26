package skilift.webserver.utilization;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import skilift.webserver.entities.Gastronomy;
import skilift.webserver.entities.GastronomyUtilization;
import skilift.webserver.entities.Lift;
import skilift.webserver.entities.LiftUtilization;
import skilift.webserver.repositories.GastronomyUtilizationRepository;
import skilift.webserver.repositories.LiftUtilizationRepository;

@Component
public class UtilizationSummaryFactory {

	@Autowired
	private LiftUtilizationRepository liftUtilizationRepository;
	@Autowired
	private GastronomyUtilizationRepository gastronomyUtilizationRepository;
	
	public UtilizationSummary createUtilizationSummaryForLift(Lift lift) {
		List<LiftUtilization> liftUtilizations = liftUtilizationRepository.findAllByLift(lift);
		Double utilization700 = new Double(0.0);
		Double utilization800 = new Double(0.0);
		Double utilization900 = new Double(0.0);
		Double utilization1000 = new Double(0.0);
		Double utilization1100 = new Double(0.0);
		Double utilization1200 = new Double(0.0);
		Double utilization1300 = new Double(0.0);
		Double utilization1400 = new Double(0.0);
		Double utilization1500 = new Double(0.0);
		Double utilization1600 = new Double(0.0);
		Double utilization1700 = new Double(0.0);
		
		Integer utilization700Count = 0;
		Integer utilization800Count = 0;
		Integer utilization900Count = 0;
		Integer utilization1000Count = 0;
		Integer utilization1100Count = 0;
		Integer utilization1200Count = 0;
		Integer utilization1300Count = 0;
		Integer utilization1400Count = 0;
		Integer utilization1500Count = 0;
		Integer utilization1600Count = 0;
		Integer utilization1700Count = 0;

		Calendar calendar = Calendar.getInstance();
    	int day = calendar.get(Calendar.DAY_OF_WEEK);
    	
		for(LiftUtilization utilization : liftUtilizations) {
			Date timestamp = utilization.getTimestamp();
			calendar.setTime(timestamp);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
			if(dayOfWeek == day &&  hourOfDay == 7 ) {
				utilization700 = utilization700 + utilization.getUtilization();
				utilization700Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 8 ) {
				utilization800 = utilization800 + utilization.getUtilization();
				utilization800Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 9 ) {
				utilization900 = utilization900 + utilization.getUtilization();
				utilization900Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 10 ) {
				utilization1000 = utilization1000 + utilization.getUtilization();
				utilization1000Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 11 ) {
				utilization1100 = utilization1100 + utilization.getUtilization();
				utilization1100Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 12 ) {
				utilization1200 = utilization1200 + utilization.getUtilization();
				utilization1200Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 13 ) {
				utilization1300 = utilization1300 + utilization.getUtilization();
				utilization1300Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 14 ) {
				utilization1400 = utilization1400 + utilization.getUtilization();
				utilization1400Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 15 ) {
				utilization1500 = utilization1500 + utilization.getUtilization();
				utilization1500Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 16 ) {
				utilization1600 = utilization1600 + utilization.getUtilization();
				utilization1600Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 17 ) {
				utilization1700 = utilization1700 + utilization.getUtilization();
				utilization1700Count++;
			}
		}
		
		ArrayList<String> timeLabels = new ArrayList<String>() { 
            { 
                add("7:00"); 
                add("8:00"); 
                add("9:00");
                add("10:00"); 
                add("11:00"); 
                add("12:00"); 
                add("13:00"); 
                add("14:00"); 
                add("15:00"); 
                add("16:00"); 
                add("17:00"); 

            } 
        };
        
        ArrayList<Double> utilizationSummary = new ArrayList<Double>();
        utilizationSummary.add(utilization700/utilization700Count);
        utilizationSummary.add(utilization800/utilization800Count);
        utilizationSummary.add(utilization900/utilization900Count);
        utilizationSummary.add(utilization1000/utilization1000Count);
        utilizationSummary.add(utilization1100/utilization1100Count);
        utilizationSummary.add(utilization1200/utilization1200Count);
        utilizationSummary.add(utilization1300/utilization1300Count);
        utilizationSummary.add(utilization1400/utilization1400Count);
        utilizationSummary.add(utilization1500/utilization1500Count);
        utilizationSummary.add(utilization1600/utilization1600Count);
        utilizationSummary.add(utilization1700/utilization1700Count);

        
		UtilizationSummary result = new UtilizationSummary();
		result.getUtilizationSummaryLabels().addAll(timeLabels);
		result.getUtilizationSummaryPerHour().addAll(utilizationSummary);
		return result;
	}
	
	
	public UtilizationSummary createUtilizationSummaryForGastronomy(Gastronomy gastronomy) {
		List<GastronomyUtilization> gastronomyUtilizations = gastronomyUtilizationRepository.findAllByGastronomy(gastronomy);
		Double utilization700 = new Double(0.0);
		Double utilization800 = new Double(0.0);
		Double utilization900 = new Double(0.0);
		Double utilization1000 = new Double(0.0);
		Double utilization1100 = new Double(0.0);
		Double utilization1200 = new Double(0.0);
		Double utilization1300 = new Double(0.0);
		Double utilization1400 = new Double(0.0);
		Double utilization1500 = new Double(0.0);
		Double utilization1600 = new Double(0.0);
		Double utilization1700 = new Double(0.0);
		
		Integer utilization700Count = 0;
		Integer utilization800Count = 0;
		Integer utilization900Count = 0;
		Integer utilization1000Count = 0;
		Integer utilization1100Count = 0;
		Integer utilization1200Count = 0;
		Integer utilization1300Count = 0;
		Integer utilization1400Count = 0;
		Integer utilization1500Count = 0;
		Integer utilization1600Count = 0;
		Integer utilization1700Count = 0;

		Calendar calendar = Calendar.getInstance();
    	int day = calendar.get(Calendar.DAY_OF_WEEK);
    	
		for(GastronomyUtilization utilization : gastronomyUtilizations) {
			Date timestamp = utilization.getTimestamp();
			calendar.setTime(timestamp);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
			if(dayOfWeek == day &&  hourOfDay == 7 ) {
				utilization700 = utilization700 + utilization.getUtilization();
				utilization700Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 8 ) {
				utilization800 = utilization800 + utilization.getUtilization();
				utilization800Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 9 ) {
				utilization900 = utilization900 + utilization.getUtilization();
				utilization900Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 10 ) {
				utilization1000 = utilization1000 + utilization.getUtilization();
				utilization1000Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 11 ) {
				utilization1100 = utilization1100 + utilization.getUtilization();
				utilization1100Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 12 ) {
				utilization1200 = utilization1200 + utilization.getUtilization();
				utilization1200Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 13 ) {
				utilization1300 = utilization1300 + utilization.getUtilization();
				utilization1300Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 14 ) {
				utilization1400 = utilization1400 + utilization.getUtilization();
				utilization1400Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 15 ) {
				utilization1500 = utilization1500 + utilization.getUtilization();
				utilization1500Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 16 ) {
				utilization1600 = utilization1600 + utilization.getUtilization();
				utilization1600Count++;
			} else if(dayOfWeek == day &&  hourOfDay == 17 ) {
				utilization1700 = utilization1700 + utilization.getUtilization();
				utilization1700Count++;
			}
		}
		
		ArrayList<String> timeLabels = new ArrayList<String>() { 
            { 
                add("7:00"); 
                add("8:00"); 
                add("9:00");
                add("10:00"); 
                add("11:00"); 
                add("12:00"); 
                add("13:00"); 
                add("14:00"); 
                add("15:00"); 
                add("16:00"); 
                add("17:00"); 

            } 
        };
        
        ArrayList<Double> utilizationSummary = new ArrayList<Double>();
        utilizationSummary.add(utilization700/utilization700Count);
        utilizationSummary.add(utilization800/utilization800Count);
        utilizationSummary.add(utilization900/utilization900Count);
        utilizationSummary.add(utilization1000/utilization1000Count);
        utilizationSummary.add(utilization1100/utilization1100Count);
        utilizationSummary.add(utilization1200/utilization1200Count);
        utilizationSummary.add(utilization1300/utilization1300Count);
        utilizationSummary.add(utilization1400/utilization1400Count);
        utilizationSummary.add(utilization1500/utilization1500Count);
        utilizationSummary.add(utilization1600/utilization1600Count);
        utilizationSummary.add(utilization1700/utilization1700Count);

        
		UtilizationSummary result = new UtilizationSummary();
		result.getUtilizationSummaryLabels().addAll(timeLabels);
		result.getUtilizationSummaryPerHour().addAll(utilizationSummary);
		return result;
	}
}
