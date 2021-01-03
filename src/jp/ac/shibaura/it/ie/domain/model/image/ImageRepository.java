package jp.ac.shibaura.it.ie.domain.model.image;

import java.util.List;

public interface ImageRepository {
    public void save(String categoryId, String URL);

    public List<Image> findAll(String categoryId);
}


