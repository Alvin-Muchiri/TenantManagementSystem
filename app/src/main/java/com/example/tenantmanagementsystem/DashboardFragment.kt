package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tenantmanagementsystem.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    // Binding for fragment_dashboard.xml
    private var _binding: FragmentDashboardBinding? = null

    // Access binding while the Fragment's view exists
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(
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

        // Get the number of tenants
        val tenantCount = TenantRepository.tenants.size

        // Calculate the total rent
        val totalRent = TenantRepository.totalRent()

        // Display the tenant count
        binding.tenantCountTextView.text = "Tenants\n$tenantCount"

        // Display the total rent
        binding.totalRentTextView.text =
            "Total rent\nKSh %,.0f".format(totalRent)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Release the binding when the view is destroyed
        _binding = null
    }
}
