package dk.easj.anbo.livedataexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dk.easj.anbo.livedataexample.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;
    private SingleWordViewModel singleWordViewModel;
    private MultipleWordsViewModel multipleWordsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        singleWordViewModel = new ViewModelProvider(getActivity()).get(SingleWordViewModel.class);
        multipleWordsViewModel = new ViewModelProvider(getActivity()).get(MultipleWordsViewModel.class);
        /*final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String word) {
                // Update the UI, in this case, a TextView.
                binding.textviewSecond.setText(word);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getWord().observe(getViewLifecycleOwner(), nameObserver);*/

        //singleWordViewModel.getWord().observe(getViewLifecycleOwner(), s -> binding.textviewSecond.setText(s));
        multipleWordsViewModel.getWords().observe(getViewLifecycleOwner(),
                strings -> binding.textviewSecond.setText(strings.toString()));

        binding.buttonSecond.setOnClickListener(view1 ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}