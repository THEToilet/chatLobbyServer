package jp.ac.shibaura.it.ie.test.data;

public class RoomWaitTestData {
    private int numberOfWaitUser;
    private boolean start;

    public boolean isStart() {
        return start;
    }

    public int getNumberOfWaitUser() {
        return numberOfWaitUser;
    }

    public void setNumberOfWaitUser(int numberOfWaitUser) {
        this.numberOfWaitUser = numberOfWaitUser;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
