package com.example.moviedatabase.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.annotation.Size
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moviedatabase.R
import com.example.moviedatabase.extension.showDialog
import com.example.moviedatabase.util.Permission
import com.example.moviedatabase.util.autoCleared
import dagger.android.support.DaggerFragment
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerFragment(),
    EasyPermissions.PermissionCallbacks {

    abstract val bindingVariable: Int

    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewDataBinding by autoCleared<T>()

    var errorMessageDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.onBackPressedDispatcher?.addCallback(this) {
            if (onBackPressed()) {
                val navController = findNavController()
                if (navController.currentDestination?.id == navController.graph.startDestination) {
                    activity?.finish()
                } else {
                    navController.navigateUp()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }
        subscriberException()
    }

    override fun onStop() {
        super.onStop()
        errorMessageDialog?.dismiss()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    internal fun hasPermission(@Size(min = 1) vararg permissions: String): Boolean {
        permissions.forEach {
            when (ContextCompat.checkSelfPermission(
                requireActivity(),
                it
            ) != PackageManager.PERMISSION_GRANTED) {
                true -> return false
            }
        }
        return true
    }

    internal fun requestPermission(rationale: String, @Size(min = 1) vararg permissions: String) {
        Permission.requestPermissions(this, rationale, PERMISSION_REQUEST_CODE, permissions)
    }

    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    open fun permissionAccepted() {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) = Unit

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) = Unit

    @SuppressLint("ShowToast")
    private fun subscriberException() {
        viewModel.run {
            httpUnauthorized.observe(viewLifecycleOwner, Observer {
                // TODO: Handle HTTP is unauthorized
            })
            unexpectedError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.unexpected_error))
            })
            httpUnavailableError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.http_unavailable_error))
            })
            rxMapperError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.rx_mapper_error))
            })
            httpForbiddenError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.http_forbidden_error))
            })
            httpGatewayTimeoutError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.no_internet_error))
            })
            errorMessage.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = it)
            })
        }
    }

    private fun handleErrorMessage(message: String) {
        if (errorMessageDialog?.isShowing != true) {
            errorMessageDialog =
                context?.showDialog(message = message, positiveMessage = getString(R.string.ok))
        }
    }

    open fun onBackPressed(): Boolean = true

    companion object {
        private const val PERMISSION_REQUEST_CODE = Activity.RESULT_FIRST_USER + 1
    }
}
