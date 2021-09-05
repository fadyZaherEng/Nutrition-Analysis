package com.example.taskapplication.ui.calculateActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taskapplication.Pogo.ResponseResult;
import com.example.taskapplication.data.NutritionClient;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class ModelViewCalculate extends ViewModel
{
    MutableLiveData<ResponseResult> mutableLiveData;
    CompositeDisposable disposable=new CompositeDisposable();
    public void GetData(String ingr)
    {
        Observable<ResponseResult> observable= NutritionClient.getINSTANCE()
                .GetData("1afb99c3","bc61ce11e9fea3a559dfcd326aeb59b5",ingr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
       disposable.add(observable.subscribe(o->mutableLiveData.setValue(o)));
    }

    public MutableLiveData<ResponseResult> getMutableLiveData(String ingr)
    {
        if (mutableLiveData==null){
            mutableLiveData=new MutableLiveData<>();
            GetData(ingr);
        }
        return mutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
