package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cexup.ui.utils.capitalizeWords

data class DetailPatient(
    var name: String = "",
    var email: String = "",
    var date_of_birth: String = "",
    var gender: String = "",
    var status: Boolean = true,
    var address: String = "",
    var phone_number: String = "",
)

@Composable
fun CardPatientProfileInformation(
    modifier: Modifier = Modifier,
    patientResponse: DetailPatient = DetailPatient(),
) {
    val listPatientInformation = listOf(
        "name",
        "email",
        "date of birth",
        "gender",
        "status",
        "address",
        "phone number",
    )

    val valuelistPatientInformation = listOf(
        patientResponse.name,
        patientResponse.email,
        patientResponse.date_of_birth,
        patientResponse.gender,
        true,
        patientResponse.address,
        patientResponse.phone_number,
    )

    Column(
        modifier = modifier
            .padding(0.dp)
    ) {
        listPatientInformation.zip(valuelistPatientInformation) { a, b ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = a.capitalizeWords())
                Text(text = b.toString())
            }
        }
    }
}