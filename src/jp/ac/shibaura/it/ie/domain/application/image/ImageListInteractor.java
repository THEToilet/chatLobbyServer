package jp.ac.shibaura.it.ie.domain.application.image;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.category.CategoryRepository;
import jp.ac.shibaura.it.ie.domain.model.image.ImageRepository;
import jp.ac.shibaura.it.ie.usecases.category.list.CategoryListOutputData;
import jp.ac.shibaura.it.ie.usecases.category.list.CategoryListInputData;
import jp.ac.shibaura.it.ie.usecases.image.ImageListInputData;
import jp.ac.shibaura.it.ie.usecases.image.ImageListOutputData;
import jp.ac.shibaura.it.ie.usecases.image.ImageListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ImageListInteractor implements ImageListUseCase {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageListOutputData handle(ImageListInputData inputData) {
        return new ImageListOutputData(imageRepository.findAll(inputData.getCategoryId()));
    }
}