package jp.ac.shibaura.it.ie.contorollers;

import jp.ac.shibaura.it.ie.domain.application.image.ImageListInteractor;
import jp.ac.shibaura.it.ie.domain.application.session.SessionInteractor;
import jp.ac.shibaura.it.ie.domain.model.session.SessionRepository;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;
import jp.ac.shibaura.it.ie.usecases.image.ImageListInputData;
import jp.ac.shibaura.it.ie.usecases.session.SessionInputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageContoroller {
    @Autowired
    private ImageListInteractor imageListInteractor;
    @Autowired
    private SessionInteractor sessionInteractor;
    @Autowired
    private LogUtils logger;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public OutputData imageList(@RequestHeader("session") String session, @RequestParam("categoryId") String categoryId) {
        if(!sessionInteractor.handle(new SessionInputData(session)).isSuccess()){
            logger.warn("認証エラー");
            throw new RuntimeException();
        }
        ImageListInputData inputData = new ImageListInputData(session, categoryId);
        return imageListInteractor.handle(inputData);
    }
}