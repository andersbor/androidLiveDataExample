package dk.easj.anbo.livedataexample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dk.easj.anbo.livedataexample.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    private SingleWordViewModel singleWordViewModel;
    private MultipleWordsViewModel multipleWordsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        singleWordViewModel = new ViewModelProvider(getActivity()).get(SingleWordViewModel.class);
        multipleWordsViewModel = new ViewModelProvider(getActivity()).get(MultipleWordsViewModel.class);

        Log.d("Apple", "has observers: " + singleWordViewModel.getWord().hasObservers());

        // Create the observer which updates the UI.
        final Observer<String> wordObserver = word -> {
            // Update the UI
            binding.textviewWords.setText(word);
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        singleWordViewModel.getWord().observe(getViewLifecycleOwner(), wordObserver);
        multipleWordsViewModel.getWords().observe(getViewLifecycleOwner(),
                strings -> binding.textviewWords.setText(strings.toString())
        );

        binding.buttonSave.setOnClickListener(view1 -> {
                    String word = binding.edittextFirst.getText().toString().trim();
                    //singleWordViewModel.getWord().setValue(word);
                    multipleWordsViewModel.add(word);
                }
        );

        binding.buttonClear.setOnClickListener(view1 ->
                //singleWordViewModel.getWord().setValue("")
                multipleWordsViewModel.clear()
        );

        binding.buttonShow.setOnClickListener(view12 ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}