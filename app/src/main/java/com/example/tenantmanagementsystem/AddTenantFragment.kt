
package com.example.tenantmanagementsystem

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystem.databinding.FragmentAddTenantBinding

class AddTenantFragment : Fragment() {

    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TENANT_LIFECYCLE", "AddTenantFragment: onCreateView")

        _binding = FragmentAddTenantBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TENANT_LIFECYCLE", "AddTenantFragment: onViewCreated")

        binding.saveButton.setOnClickListener {

            // Read the tenant details
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            // Validate the fields
            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Validate the phone number
            if (phone.filter { it.isDigit() }.length < 10) {
                binding.phoneEditText.error = "Enter a valid phone number"
                return@setOnClickListener
            }

            // Create and save the tenant
            val tenant = Tenant(name, phone, rent)
            TenantRepository.addTenant(tenant)

            // Pass the tenant to the View Tenants screen
            val action = AddTenantFragmentDirections
                .actionAddTenantFragmentToViewTenantsFragment(tenant)

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("TENANT_LIFECYCLE", "AddTenantFragment: onDestroyView")

        _binding = null
    }
}
