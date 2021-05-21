package dk.easj.anbo.livedataexample;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SingleWordViewModel extends ViewModel {
    private MutableLiveData<String> word;

    public MutableLiveData<String> getWord() {
        if (word == null) {
            word = new MutableLiveData<>();
        }
        return word;
    }
}
