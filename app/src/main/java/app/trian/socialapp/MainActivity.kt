package app.trian.socialapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.cexup.ui.corporate.screen.BaseScreen
import com.cexup.ui.corporate.theme.CorporateTheme
import com.cexup.ui.R
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.screen.PhysicalExamination
import com.cexup.ui.corporate.screen.ScreenCheckup

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CorporateTheme {
                BaseScreen(
                    currentRoute = "",
                    onSearchPatient = {  },
                    onPatientDetail = {  },
                    onCheckUp = {  },
                    onAddPatient = {  },
                    onProfile = {  },
                    onLogout = {  },
                    onNavigate = {  },
                    listMenuSidebar = listOf(
                        SidebarMenuModel(
                            name= R.string.corporate_menu_dashboard,
                            image = R.drawable.ic_home_unselected,
                            selectedImage = R.drawable.ic_home_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_patients,
                            image = R.drawable.ic_patient_unselected ,
                            selectedImage = R.drawable.ic_patient_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_cexup,
                            image = R.drawable.ic_feature_unselected ,
                            selectedImage = R.drawable.ic_feature_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_account,
                            image = R.drawable.ic_user_unselected ,
                            selectedImage = R.drawable.ic_user_selected,
                            type= SidebarMenuType.Link,
                            path = ""
                        ),
                        SidebarMenuModel(
                            name= R.string.corporate_menu_logout,
                            image = R.drawable.ic_logout_unselected ,
                            selectedImage = R.drawable.ic_logout_selected,
                            type= SidebarMenuType.Button,
                            path = ""
                        ),
                    )
                ) {
                    val context = LocalContext.current
                    val featureList = listOf(
                        PhysicalExamination(
                            icon = R.drawable.ic_multiparameter,
                            nameFeature = "Feature 1",
                            typePhysicalExamination = TypePhysicalExamination.NoValue,
                        ) {
                            Toast.makeText(context, "Feature 1 $it", Toast.LENGTH_SHORT).show()
                        },
                        PhysicalExamination(
                            icon = R.drawable.ic_multiparameter,
                            nameFeature = "Feature 2",
                            typePhysicalExamination = TypePhysicalExamination.NoValue,
                        ) {
                            Toast.makeText(context, "Feature 2 $it", Toast.LENGTH_SHORT).show()
                        }
                    )
                    val patients = listOf(
                        Pair(first = "Name 1", second = "UserCode 1"),
                        Pair(first = "Name 2", second = "UserCode 2"),
                        Pair(first = "Name 3", second = "UserCode 3"),
                        Pair(first = "Name 4", second = "UserCode 4"),
                    )
                    ScreenCheckup(
                        featureList = featureList,
                        onClickSyncToCloud = {
                            Toast.makeText(context, "Sync to Cloud", Toast.LENGTH_SHORT).show()
                        },
                        patients = patients,
                        onPatientSelected = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
