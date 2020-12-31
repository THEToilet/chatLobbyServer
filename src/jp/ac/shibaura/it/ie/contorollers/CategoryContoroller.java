package jp.ac.shibaura.it.ie.contorollers;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.application.category.CategoryJoinInteractor;
import jp.ac.shibaura.it.ie.domain.application.category.CategoryListInteractor;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinInputData;
import jp.ac.shibaura.it.ie.usecases.category.list.CategoryListInputData;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryContoroller {

    @Inject
    private CategoryListInteractor categoryListInteractor;
    @Inject
    private CategoryJoinInteractor categoryJoinInteractor;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public OutputData categoryList(@RequestParam("session") String session, @RequestParam("userId") String userId) {
        CategoryListInputData inputData = new CategoryListInputData(session);
        return categoryListInteractor.handle(inputData);
    }

    @RequestMapping(value = "/{categoryId}/join", method = RequestMethod.GET)
    public OutputData categoryJoin(@RequestParam("session") String session, @RequestParam("userId") String userId, @PathVariable("categoryId") String categoryId) {
        CategoryJoinInputData inputData = new CategoryJoinInputData(session, userId, categoryId);
        return categoryJoinInteractor.handle(inputData);
    }
}