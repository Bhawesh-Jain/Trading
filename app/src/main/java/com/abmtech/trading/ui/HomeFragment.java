package com.abmtech.trading.ui;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abmtech.trading.R;
import com.abmtech.trading.adapter.LivePricesAdapter;
import com.abmtech.trading.adapter.ServicesAdapter;
import com.abmtech.trading.databinding.FragmentHomeBinding;
import com.abmtech.trading.model.PriceModel;
import com.abmtech.trading.model.ServiceModel;
import com.abmtech.trading.utils.ProgressDialog;
import com.abmtech.trading.utils.Session;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private FirebaseFirestore db;
    private ProgressDialog pd;

    final int duration = 20;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            binding.priceRecycler.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        db = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(getContext());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Session session = new Session(getContext());

        if (session.isLoggedIn()) binding.llLoginRedirect.setVisibility(View.GONE);

        binding.icCertified.setOnClickListener(v -> startActivity(new Intent(getContext(), ComplianceActivity.class)));
        binding.textLogin.setOnClickListener(v -> startActivity(new Intent(getContext(), LoginActivity.class)));
        binding.textRegister.setOnClickListener(v -> startActivity(new Intent(getContext(), SignupActivity.class)));
        getTransaction();
        getServices();
    }

    private void getTransaction() {
        CollectionReference ref = db.collection("prices");

        ref.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        pd.dismiss();
                        if (task.getResult().isEmpty()) {
                            Toast.makeText(getContext(), "No Transaction Found!", Toast.LENGTH_SHORT).show();
                        } else {
                            List<PriceModel> data = task.getResult().toObjects(PriceModel.class);

                            if (data.size() > 0) {
                                LivePricesAdapter adapter = new LivePricesAdapter(getContext(), data);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

                                binding.priceRecycler.setLayoutManager(layoutManager);
                                binding.priceRecycler.setAdapter(adapter);

                                binding.priceRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                        super.onScrolled(recyclerView, dx, dy);
                                        int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                                        if (lastItem == layoutManager.getItemCount() - 1) {
                                            mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                                            Handler postHandler = new Handler();
                                            postHandler.postDelayed(() -> {
                                                recyclerView.setAdapter(adapter);
                                                mHandler.postDelayed(SCROLLING_RUNNABLE, 0);
                                            }, 0);
                                        }
                                    }
                                });
                                mHandler.postDelayed(SCROLLING_RUNNABLE, 0);

                            } else {
                                Toast.makeText(getContext(), "No transaction found!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Log.e(TAG, "Error =>", task.getException());
                        pd.dismiss();
                    }
                });
    }

    private void getServices() {
        CollectionReference ref = db.collection("services");

        ref.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        pd.dismiss();
                        if (task.getResult().isEmpty()) {
                            Toast.makeText(getContext(), "No Services Found!", Toast.LENGTH_SHORT).show();
                        } else {
                            List<ServiceModel> data = task.getResult().toObjects(ServiceModel.class);

                            if (data.size() > 0) {
                                binding.serviceRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                binding.serviceRecycler.setAdapter(new ServicesAdapter(data, getContext()));
                            } else {
                                Toast.makeText(getContext(), "No Services found!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Log.e(TAG, "Error =>", task.getException());
                        pd.dismiss();
                    }
                });
    }

}