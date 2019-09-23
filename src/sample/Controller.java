package sample;

import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    public Line citou;

    public double getX(int a) {
        return (double) a / 1500.0 * (583.0 - 15.0) + 15.0;
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

    public void fcfs_start() {
        int start = Integer.parseInt(start_position.getText());
        Path path = new Path();
        double x = citou.getLayoutX();
        double y = citou.getLayoutY();
        path.getElements().add(new MoveTo(15, y));
//        path.getElements().add(new LineTo(100, y));
//        path.getElements().add(new MoveTo(getX(start), y));
//        for (Integer i : fcfs) {
//            path.getElements().add(new LineTo(getX(i), y));
//            System.out.println(getX(i));
//        }

        PathTransition pathTransition = new PathTransition();//创建一个动画对象
        pathTransition.setDuration(Duration.seconds(1));//动画持续时间 0.5秒
        pathTransition.setPath(path);//把我们设置好的动画路径放入里面
        pathTransition.setNode(citou);//给动画添加组件，让某个组件来完成这个动画
        pathTransition.setCycleCount(1);//执行1遍
        pathTransition.play();//执行动画
    }
}
