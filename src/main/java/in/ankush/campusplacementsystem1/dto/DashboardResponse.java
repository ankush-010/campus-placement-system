package in.ankush.campusplacementsystem1.dto;

public class DashboardResponse {

    private long totalStudents;
    private long totalCompanies;
    private long totalDrives;
    private long totalApplications;

    private long appliedApplications;
    private long shortlistedApplications;
    private long selectedApplications;
    private long rejectedApplications;
    private double selectionRate;

    public DashboardResponse() {
    }

    public DashboardResponse(
            long totalStudents,
            long totalCompanies,
            long totalDrives,
            long totalApplications,
            long appliedApplications,
            long shortlistedApplications,
            long selectedApplications,
            long rejectedApplications,
            double selectionRate) {

        this.totalStudents = totalStudents;
        this.totalCompanies = totalCompanies;
        this.totalDrives = totalDrives;
        this.totalApplications = totalApplications;
        this.appliedApplications = appliedApplications;
        this.shortlistedApplications = shortlistedApplications;
        this.selectedApplications = selectedApplications;
        this.rejectedApplications = rejectedApplications;
        this.selectionRate = selectionRate;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalCompanies() {
        return totalCompanies;
    }

    public long getTotalDrives() {
        return totalDrives;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public long getAppliedApplications() {
        return appliedApplications;
    }

    public long getShortlistedApplications() {
        return shortlistedApplications;
    }

    public long getSelectedApplications() {
        return selectedApplications;
    }

    public long getRejectedApplications() {
        return rejectedApplications;
    }

    public double getSelectionRate() {
        return selectionRate;
    }
}