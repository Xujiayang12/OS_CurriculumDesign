package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.beans.EventHandler;
import java.util.List;

public class Controller {
    public Scheduler scheduler;
    public List<Integer> track;
    public List<Integer> fcfs;
    public List<Integer> sstf;
    public List<Integer> scan;
    public List<Integer> cscan;
    public ObservableList dataList;
    public TextField start_position;
    public Text fcfstext;
    public Text sstftext;
    public Text scantext;
    public Text cscantext;
    public TableColumn oritable;
    public TableColumn fcfstable;
    public TableColumn sstftable;
    public TableColumn scantable;
    public TableColumn cscantable;
    public TableView cidaotable;
    public Rectangle head;
    public PathTransition pathTransition;
    public Text status;

    public double getX(int a) {
        return (double) a / 1500.0 * 567.0 + -257;
    }

    public void startRender() {
        int start = Integer.parseInt(start_position.getText());
        scheduler = new Scheduler(start);
        track = scheduler.getTrack_number();
        fcfs = scheduler.FCFS_Schedule();
        sstf = scheduler.SSTF_Schedule();
        scan = scheduler.SCAN_Schedule();
        cscan = scheduler.C_SCAN_Schedule();
        fcfstext.setText(scheduler.getTrackSeek(fcfs));
        sstftext.setText(scheduler.getTrackSeek(sstf));
        scantext.setText(scheduler.getTrackSeek(scan));
        cscantext.setText(scheduler.getTrackSeek(cscan));
        dataList = FXCollections.observableArrayList();
        for (int i = 0; i < 400; i++) {
            dataList.add(new TrackData(String.valueOf(track.get(i)), String.valueOf(fcfs.get(i)), String.valueOf(sstf.get(i)), String.valueOf(scan.get(i)), String.valueOf(cscan.get(i))));
        }
        oritable.setCellValueFactory(new PropertyValueFactory<>("track_num"));
        fcfstable.setCellValueFactory(new PropertyValueFactory<>("fcfs_num"));
        sstftable.setCellValueFactory(new PropertyValueFactory<>("sstf_num"));
        scantable.setCellValueFactory(new PropertyValueFactory<>("scan_num"));
        cscantable.setCellValueFactory(new PropertyValueFactory<>("cscan_num"));
        cidaotable.setItems(dataList);
    }

    public void start_animation(List<Integer> track, int seconds, String name) {
        if (pathTransition != null) {
            pathTransition.stop();
        }
        if (track == null) {
            status.setText("您还未初始化一组随机数");
        } else {
            int start = Integer.parseInt(start_position.getText());
            int y = 30;
            status.setText("策略：" + name + "  状态：进行中");
            Path path = new Path();
            path.getElements().add(new MoveTo(getX(start), y));
            for (Integer i : track) {
                path.getElements().add(new LineTo(getX(i), y));
            }
            pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(seconds));
            pathTransition.setPath(path);
            pathTransition.setNode(head);
            pathTransition.setCycleCount(1);
            pathTransition.play();
            pathTransition.setOnFinished(actionEvent -> status.setText("策略：" + name + "  状态：结束"));
        }
    }

    public void fcfs_start() {
        start_animation(fcfs, 60, "FCFS");
    }

    public void sstf_start() {
        start_animation(sstf, 10, "SSTF");
    }

    public void scan_start() {
        start_animation(scan, 10, "电梯LOOK");
    }

    public void cscan_start() {
        start_animation(cscan, 10, "C-SCAN");
    }
}
