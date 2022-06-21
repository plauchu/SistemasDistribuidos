package mx.itam.packages.jmsqueuesthepotatogame;

import java.io.Serializable;

public class Potato implements Serializable {

    private String id;
    private int remainingTime;

    public Potato(String id, int remainingTime) {
        this.id = id;
        this.remainingTime = remainingTime;
    }

    public String getId() {
        return id;
    }

    public void decreaseRemainingTime() {
        remainingTime--;
    }

    public boolean isDropped() {
        return remainingTime == 0;
    }
}

