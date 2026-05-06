package com.example.banking.auth

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Biometric authentication manager for secure banking operations.
 * Supports fingerprint and face authentication.
 */
class BiometricAuthManager(
	private val context:Context,
		private val securityPreferences  :  SecurityPreferences
){

	data class AuthResult(
val success:Boolean,
			val errorMessage  :  String?=null,
	val authenticationType:AuthType?=null
	)

	enum class AuthType{
		FINGERPRINT   ,
			FACE,
	IRIS,
		NONE
	}

	/**
     * Check if biometric authentication is available on this device.
     */
	fun isBiometricAvailable()  :  Boolean{
val biometricManager=androidx.biometric.BiometricManager.
from(context)

		return when(biometricManager.canAuthenticate(
androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
		))
{
			androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS->true
			else->false
		}
	}

	/**
     * Authenticate user with biometric credentials.
     * Returns result with success status and optional error message.
     */
	suspend fun authenticate(
		activity:FragmentActivity,
title:String="Authentication Required",
			subtitle  :  String="Verify your identity to continue",
	description:String="Use your biometric credential to authenticate"
	):AuthResult=suspendCancellableCoroutine{continuation->

		val executor=ContextCompat.getMainExecutor(context)

val biometricPrompt=BiometricPrompt(
			activity,
	executor,
			object:BiometricPrompt.AuthenticationCallback()
{

				override fun onAuthenticationSucceeded(
result:BiometricPrompt.AuthenticationResult
				){
					super.onAuthenticationSucceeded(result)

					val authType=when(result.authenticationType){
BiometricPrompt.AUTHENTICATION_RESULT_TYPE_BIOMETRIC->
							AuthType.FINGERPRINT
						else->AuthType.NONE
					}

securityPreferences.setLastAuthTime(System.currentTimeMillis())

					continuation.resume(
AuthResult(
							success=true,
				authenticationType=authType
						)
					)
				}

				override fun onAuthenticationError(errorCode  :  Int,errString:
CharSequence){
					super.onAuthenticationError(errorCode,errString)

continuation.resume(
						AuthResult(
							success=false,
errorMessage=errString.toString()
						)
					)
				}

				override fun onAuthenticationFailed()
{
					super.onAuthenticationFailed()
					// Don't resume here, user can retry
				}
			}
		)

		val promptInfo=BiometricPrompt.PromptInfo.Builder()
.setTitle(title)
			.setSubtitle(subtitle)
				.setDescription(description)
			.setNegativeButtonText("Cancel")
			.setConfirmationRequired(true)
.build()

		biometricPrompt.authenticate(promptInfo)

		continuation.invokeOnCancellation{
			biometricPrompt.cancelAuthentication()
		}
	}

	/**
     * Authenticate with cryptographic operation.
     * Useful for encrypting/decrypting sensitive data.
     */
	suspend fun authenticateWithCrypto(
activity:FragmentActivity,
		cryptoObject  :  BiometricPrompt.CryptoObject
	):Pair<Boolean,BiometricPrompt.CryptoObject?>=
suspendCancellableCoroutine{continuation->

			val executor=ContextCompat.getMainExecutor(context)

			val biometricPrompt=BiometricPrompt(
				activity,
executor,
				object:BiometricPrompt.AuthenticationCallback()
{

					override fun onAuthenticationSucceeded(
						result  :  BiometricPrompt.AuthenticationResult
					){
continuation.resume(Pair(true,result.cryptoObject))
					}

					override fun onAuthenticationError(errorCode:Int,
errString:CharSequence){
						continuation.resume(Pair(false,null))
					}
				}
			)

			val promptInfo=BiometricPrompt.PromptInfo.Builder()
				.setTitle("Cryptographic Authentication")
.setSubtitle("Secure your transaction")
				.setNegativeButtonText("Cancel")
				.build()

			biometricPrompt.authenticate(promptInfo,cryptoObject)
		}

	/**
     * Get available authentication types on this device.
     */
	fun getAvailableAuthTypes():List<AuthType>
{
		val types=mutableListOf<AuthType>()

		if(isBiometricAvailable()){
types.add(AuthType.FINGERPRINT)
			// Add more types based on device capabilities
		}

		return types
	}
}
// Updated: 2026-03-30
// Last reviewed: 2026-05-06
