package com.example.demo_basic_staff_android.calculator

import androidx.lifecycle.*
import com.example.demo_basic_staff_android.database.History
import com.example.demo_basic_staff_android.database.HistoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
* AndroidViewModel provides Application context
If you need to use context inside your ViewModel you should use AndroidViewModel (AVM),
* because it contains the application context. To retrieve the context call getApplication(),
*  otherwise use the regular ViewModel (VM).

AndroidViewModel has application context.
*  We all know having static context instance is evil as it can cause memory leaks!! However,
*  having static Application instance is not as bad as you might think
*  because there is only one Application instance in the running application.

Therefore, using and having Application instance in a specific class is not a problem in general.
*  But, if an Application instance references them, it is a problem because of the reference cycle problem.
* so to use applicationViewModel :
* class CalculatorViewModel(application: Application) : AndroidViewModel(application) {}
* */
data class Buttons(
    val one : String = "1",
    val two : String = "2",
    val three : String = "3",
    val four : String = "4",
    val five : String = "5",
    val six : String = "6",
    val seven : String = "7",
    val eight : String = "8",
    val nine : String = "9",
    val zero : String = "0",
    val sign :String = "x-1",
    val plus : String = "+",
    val minus : String = "-",
    val multiply :String = "x",
    val divided :String = "/",
    val point : String = ".",
    val pi : String = Math.PI.toString(),
)

class CalculatorViewModel(
    private val database: HistoryDao,
) :ViewModel(){
    private var _result = MutableLiveData<String>()
    private var _addOperand  = MutableLiveData<Boolean>()
    var histories = database.getAllHistories()

    val result: LiveData<String>
        get() = _result

    fun addToOperation(string : String){
        val op:List<String> = mutableListOf("+","-","/","x")
        if(op.contains(string)){
            if(_addOperand.value == false){
                _result.value = _result.value+string
                _addOperand.value = true
            }
        }else{
            _result.value = _result.value+string
        }

    }
    fun cancelOperation(){

        _addOperand.value = false
        _result.value = "="
    }
    fun removeFromOperation(){
        val op:List<String> = mutableListOf("+","-","/","x")
        if(!op.contains(_result.value))_addOperand.value = false
        if(_result.value!=null) if(_result.value!!.length>1) _result.value=_result.value!!.dropLast(1)
    }
    fun resultOperation() {
        viewModelScope.launch{
            val result: String = _result.value!!.removePrefix("=")
            var op1: Double
            val op2: Double
            val split: List<String>
            val newOperation = History()
            newOperation.operation = ">"+result
        when {
            result.contains("-") -> {
                split = result.split("-")
                if (split[0].isNotEmpty()) {
                    op1 = split[0].toDouble()
                    op2 = if (split[1].isEmpty()) 0.0 else split[1].toDouble()
                    op1 -= op2

                    _result.value = "=" + op1.toString()

                    newOperation.result = _result.value!!

                    _addOperand.value = false
                    insert(newOperation)
                } else _result.value = "=Error"
            }
            result.contains("x") -> {
                split = result.split("x")
                if (split[0].isNotEmpty()) {
                    op1 = split[0].toDouble()
                    op2 = if (split[1].isEmpty()) 0.0 else split[1].toDouble()
                    op1 *= op2
                    _result.value = "=" + op1.toString()
                    newOperation.result = _result.value!!

                    _addOperand.value = false
                    insert(newOperation)
                } else _result.value = "=Error"
            }
            result.contains("/") -> {
                split = result.split("/")
                if (split[0].isNotEmpty()) {
                    op1 = split[0].toDouble()
                    op2 = if (split[1].isEmpty()) 0.0 else split[1].toDouble()
                    op1 /= op2
                    _result.value = "=" + op1.toString()
                    newOperation.result = _result.value!!

                    _addOperand.value = false
                    insert(newOperation)
                } else _result.value = "=Error"
            }
            result.contains("+") -> {
                split = result.split("+")
                if (split[0].isNotEmpty()) {
                    op1 = split[0].toDouble()
                    op2 = if (split[1].isEmpty()) 0.0 else split[1].toDouble()
                    op1 += op2
                    _result.value = "=" + op1.toString()
                    newOperation.result = _result.value!!

                    _addOperand.value = false
                    insert(newOperation)
                } else _result.value = "=Error"
            }
        }
                    }

    }





    init {
        _result.value = "="
        _addOperand.value = false
        initializeHistory()
    }
    private fun initializeHistory() {
        viewModelScope.launch {
            histories = database.getAllHistories()
        }
    }


    private suspend fun clearHistoryTable() { // suspend seems like await in dart not same
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onClear(){
        viewModelScope.launch {
            clearHistoryTable()
        }
    }
    private suspend fun insert(operationSyntax: History) {
        withContext(Dispatchers.IO) {
            database.insert(operationSyntax)
        }
    }

    override fun onCleared() {
        _result.value = "="
        _addOperand.value = false
        super.onCleared()
    }
}