package com.example.ssutudy.study

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.ssutudy.R
import com.example.ssutudy.study.enum.MainFragments
import com.example.ssutudy.study.models.dto.StudyLogResponseDto
import com.example.ssutudy.study.ui.home.HomeFragment
import com.example.ssutudy.study.ui.mystudy.MyStudyFragment
import com.example.ssutudy.study.ui.settings.SettingsFragment
import com.example.ssutudy.util.service.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment : HomeFragment
    private lateinit var myStudyFragment: MyStudyFragment
    private lateinit var settingsFragment : SettingsFragment
    private lateinit var studyService: StudyService
    private val originDate = "2021-01-01"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        studyService = ServiceGenerator.createService(StudyService::class.java)


        homeFragment = HomeFragment()
        myStudyFragment = MyStudyFragment()
        settingsFragment = SettingsFragment()
        setFragment(MainFragments.HOME)

        navView.setOnNavigationItemSelectedListener(MainNavigationItemSelectedListener())
        requestTotalStudyLog()
    }

    fun requestTotalStudyLog() {
        val today = Date(System.currentTimeMillis())
        val request = studyService.requestStudyLog(originDate, dateFormat.format(today))
        request.enqueue(object : Callback<StudyLogResponseDto> {
            override fun onResponse(call: Call<StudyLogResponseDto>, response: Response<StudyLogResponseDto>) {
                Log.d("Auth", "onResponse : ${response.code()}")
                Log.d("Auth", "id : ${response.body()!!.studyLogs.get(0).study!!.id}, limit : ${response.body()!!.studyLogs.get(0).study!!.participantLimit}")
                when(response.code()) {
                    200 -> {

                    }
                    401 -> {

                    }
                }
            }

            override fun onFailure(call: Call<StudyLogResponseDto>, t: Throwable) {
                Log.d("Auth", "onFailure : ${t.localizedMessage}")
            }
        })
    }

    inner class MainNavigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId) {
                R.id.navigation_home -> {
                    setFragment(MainFragments.HOME)
                    return true
                }
                R.id.navigation_mystudy -> {
                    setFragment(MainFragments.MYSTUDY)
                    return true
                }
                R.id.navigation_settings -> {
                    setFragment(MainFragments.SETTINGS)
                    return true
                }
            }
            return false
        }
    }

    fun setFragment(flag : MainFragments) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when(flag) {
            MainFragments.HOME -> fragmentTransaction.replace(R.id.main_fragment_frame, homeFragment).commit()
            MainFragments.MYSTUDY -> fragmentTransaction.replace(R.id.main_fragment_frame, myStudyFragment).commit()
            MainFragments.SETTINGS -> fragmentTransaction.replace(R.id.main_fragment_frame, settingsFragment).commit()
        }
    }
}