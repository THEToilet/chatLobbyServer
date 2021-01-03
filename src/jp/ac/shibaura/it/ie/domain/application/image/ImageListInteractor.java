package jp.ac.shibaura.it.ie.domain.application.image;

import jp.ac.shibaura.it.ie.domain.model.image.Image;
import jp.ac.shibaura.it.ie.domain.model.image.ImageRepository;
import jp.ac.shibaura.it.ie.usecases.image.ImageListInputData;
import jp.ac.shibaura.it.ie.usecases.image.ImageListOutputData;
import jp.ac.shibaura.it.ie.usecases.image.ImageListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageListInteractor implements ImageListUseCase {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageListOutputData handle(ImageListInputData inputData) {
        List<Image> urls = imageRepository.findAll(inputData.getCategoryId());
        return new ImageListOutputData(urls);
    }
}