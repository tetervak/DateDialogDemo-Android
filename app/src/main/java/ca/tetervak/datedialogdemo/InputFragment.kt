package ca.tetervak.datedialogdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ca.tetervak.datedialogdemo.databinding.InputFragmentBinding
import ca.tetervak.datedialogdemo.dialogs.DateDialog
import java.util.*

class InputFragment : Fragment() {

    companion object{
        const val DATE_REQUEST: Int = 1
        const val DATE_KEY: String = "date"
    }

    private lateinit var date: Date
    private lateinit var binding: InputFragmentBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = InputFragmentBinding.inflate(inflater, container, false)

        date = if(savedInstanceState is Bundle){
            savedInstanceState.getSerializable(DATE_KEY) as Date
        }else{
            Date()
        }
        binding.date = date

        navController =  findNavController()

        // make the date dialog work
        binding.editButton.setOnClickListener {
            val action =
                InputFragmentDirections.actionInputFragmentToDateDialog(
                    DATE_REQUEST, getString(R.string.edit_date), date)
            navController.navigate(action)
        }
        DateDialog.setResultListener(this, R.id.inputFragment){
            if(it?.requestCode == DATE_REQUEST){
                date = it.date
                binding.date = date
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(DATE_KEY, date)
    }
}