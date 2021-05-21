package dk.easj.anbo.livedataexample;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MultipleWordsViewModel extends ViewModel {
    private List<String> words = new ArrayList<>();
    private MutableLiveData<List<String>> mutableLiveData; // observable wrapper on original data

    public MutableLiveData<List<String>> getWords() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void add(String newWord) {
        words.add(newWord);
        mutableLiveData.setValue(words); // notifies observers
    }

    public void clear() {
        words.clear();
        mutableLiveData.setValue(words);
    }

}
