package jp.ac.shibaura.it.ie.test.data;

import jp.ac.shibaura.it.ie.test.data.CategoryData;

import java.util.ArrayList;
import java.util.List;

public class CategoryListTestData {
    private List<CategoryData> categoryList = new ArrayList<>();

    public List<CategoryData> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryData> categoryList) {
        this.categoryList = categoryList;
    }
}
