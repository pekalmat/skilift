package skilift.webserver.utilization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UtilizationSummary implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Double> utilizationSummaryPerHour;
	private List<String> utilizationSummaryLabels;
	
	public UtilizationSummary() {
		this.utilizationSummaryPerHour = new ArrayList<Double>();
		this.utilizationSummaryLabels = new ArrayList<String>();
	}

	public List<Double> getUtilizationSummaryPerHour() {
		return utilizationSummaryPerHour;
	}

	public void setUtilizationSummaryPerHour(List<Double> utilizationSummaryPerHour) {
		this.utilizationSummaryPerHour = utilizationSummaryPerHour;
	}

	public List<String> getUtilizationSummaryLabels() {
		return utilizationSummaryLabels;
	}

	public void setUtilizationSummaryLabels(List<String> utilizationSummaryLabels) {
		this.utilizationSummaryLabels = utilizationSummaryLabels;
	}
}
