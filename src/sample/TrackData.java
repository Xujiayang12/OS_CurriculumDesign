package sample;

import javafx.beans.property.SimpleStringProperty;

public class TrackData {
    public SimpleStringProperty track_num = new SimpleStringProperty();
    public SimpleStringProperty fcfs_num = new SimpleStringProperty();
    public SimpleStringProperty sstf_num = new SimpleStringProperty();
    public SimpleStringProperty scan_num = new SimpleStringProperty();
    public SimpleStringProperty cscan_num = new SimpleStringProperty();

    public TrackData(String track, String fcfs, String sstf, String scan, String cscan) {
        track_num.set(track);
        fcfs_num.set(fcfs);
        sstf_num.set(sstf);
        scan_num.set(scan);
        cscan_num.set(cscan);
    }

    public String getTrack_num() {
        return track_num.get();
    }

    public SimpleStringProperty track_numProperty() {
        return track_num;
    }

    public void setTrack_num(String track_num) {
        this.track_num.set(track_num);
    }

    public String getFcfs_num() {
        return fcfs_num.get();
    }

    public SimpleStringProperty fcfs_numProperty() {
        return fcfs_num;
    }

    public void setFcfs_num(String fcfs_num) {
        this.fcfs_num.set(fcfs_num);
    }

    public String getSstf_num() {
        return sstf_num.get();
    }

    public SimpleStringProperty sstf_numProperty() {
        return sstf_num;
    }

    public void setSstf_num(String sstf_num) {
        this.sstf_num.set(sstf_num);
    }

    public String getScan_num() {
        return scan_num.get();
    }

    public SimpleStringProperty scan_numProperty() {
        return scan_num;
    }

    public void setScan_num(String scan_num) {
        this.scan_num.set(scan_num);
    }

    public String getCscan_num() {
        return cscan_num.get();
    }

    public SimpleStringProperty cscan_numProperty() {
        return cscan_num;
    }

    public void setCscan_num(String cscan_num) {
        this.cscan_num.set(cscan_num);
    }
}
