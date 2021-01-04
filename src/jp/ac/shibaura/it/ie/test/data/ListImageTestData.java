package jp.ac.shibaura.it.ie.test.data;

import jp.ac.shibaura.it.ie.domain.model.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ListImageTestData {
    public List<ImageTest> urls = new ArrayList<>();
    public static class ImageTest{
        public String imageUrl;
    }
}
