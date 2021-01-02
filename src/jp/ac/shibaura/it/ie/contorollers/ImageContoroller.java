package jp.ac.shibaura.it.ie.contorollers;

import jp.ac.shibaura.it.ie.domain.application.image.ImageListInteractor;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;
import jp.ac.shibaura.it.ie.usecases.image.ImageListInputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageContoroller {
    @Autowired
    private ImageListInteractor imageListInteractor;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public OutputData imageList(@RequestHeader("session") String session, @RequestParam("categoryId") String categoryId) {
        ImageListInputData inputData = new ImageListInputData(session, categoryId);
        return imageListInteractor.handle(inputData);
    }
}