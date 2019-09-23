package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Scheduler {
    private List<Integer> track_number;
    private int start_position;

    public Scheduler(int start_position) {
        this.track_number = new ArrayList<>();
        this.start_position = start_position;
        this.generateRandomTrackNumber();
    }

    public void generateRandomTrackNumber() {
        Random r = new Random();
        for (int i = 0; i < 200; i++) {
            this.track_number.add(r.nextInt(500));
        }
        for (int i = 0; i < 100; i++) {
            this.track_number.add(r.nextInt(500) + 500);
        }
        for (int i = 0; i < 100; i++) {
            this.track_number.add(r.nextInt(500) + 1000);
        }
        Collections.shuffle(this.track_number);
    }

    public int getNearest(int position, List<Integer> array) {
        int min = 2000;
        int index = 2000;
        Integer minPosition = 2000;
        for (int i = 0; i < array.size(); i++) {
            Integer num = array.get(i);
            int gap = Math.abs(num - position);
            if (gap < min) {
                index = i;
                minPosition = num;
                min = gap;
            }
        }
        return index;
    }

    public boolean getSizeBoolean(List<Integer> a) {
        return a.size() > 0;
    }

    public List<Integer> FCFS_Schedule() {
        return this.track_number;
    }

    public List<Integer> SSTF_Schedule() {
        int position = this.start_position;
        List<Integer> before_schedule = new ArrayList<>(this.track_number);
        List<Integer> new_track_number = new ArrayList<>();
        while (getSizeBoolean(before_schedule)) {
            int min_index = getNearest(position, before_schedule);
            int min_position = before_schedule.get(min_index);
            new_track_number.add(min_position);
            position = min_position;
            before_schedule.remove(min_index);
        }
        return new_track_number;
    }

    public List<Integer> SCAN_Schedule() {
        List<Integer> forward = new ArrayList<>();
        List<Integer> backward = new ArrayList<>();
        List<Integer> new_track_number = new ArrayList<>();
        for (Integer i : this.track_number) {
            if (i >= this.start_position) {
                forward.add(i);
            } else {
                backward.add(i);
            }
        }
        Collections.sort(forward);
        Collections.sort(backward);
        Collections.reverse(backward);
        new_track_number.addAll(forward);
        new_track_number.addAll(backward);
        return new_track_number;
    }

    public List<Integer> C_SCAN_Schedule() {
        List<Integer> forward = new ArrayList<>();
        List<Integer> backward = new ArrayList<>();
        List<Integer> new_track_number = new ArrayList<>();
        for (Integer i : this.track_number) {
            if (i >= this.start_position) {
                forward.add(i);
            } else {
                backward.add(i);
            }
        }
        Collections.sort(forward);
        Collections.sort(backward);
        new_track_number.addAll(forward);
        new_track_number.addAll(backward);
        return new_track_number;
    }

    public String getTrackSeek(List<Integer> new_track_num) {
        int position = this.start_position;
        int seek = 0;
        for (Integer i : new_track_num) {
            seek += Math.abs(position - i);
            position = i;
        }
        return String.valueOf(seek);
    }

    public List<Integer> getTrack_number() {
        return track_number;
    }

    public void setTrack_number(List<Integer> track_number) {
        this.track_number = track_number;
    }
}

