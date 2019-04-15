package com.iitg.interaction.facultystudentinteractionportal;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class CourseInformationFragmentStudent extends Fragment {

    DatabaseReference databaseReference;
    public ArrayList<Event> events;
    public ArrayList<CourseMaterial> materials;
    public int count=0;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final TextView textView = getView().findViewById(R.id.textView4);
        final TextView fullname_tv = getView().findViewById(R.id.textView7);
        final TextView description_tv  = getView().findViewById(R.id.editText6);
        final TextView syllabus_tv = getView().findViewById(R.id.editText7);
        final TextView marks_tv = getView().findViewById(R.id.editText8);
        final TextView time_slots = getView().findViewById(R.id.textView15);

        getView().findViewById(R.id.textView11).setVisibility(View.GONE);
        getView().findViewById(R.id.editText6).setVisibility(View.GONE);
        getView().findViewById(R.id.textView12).setVisibility(View.GONE);
//        getView().findViewById(R.id.textView9).setVisibility(View.GONE);
//        getView().findViewById(R.id.textView8).setVisibility(View.GONE);
        getView().findViewById(R.id.editText7).setVisibility(View.GONE);
        getView().findViewById(R.id.textView13).setVisibility(View.GONE);
        getView().findViewById(R.id.editText8).setVisibility(View.GONE);
        getView().findViewById(R.id.textView14).setVisibility(View.GONE);
        getView().findViewById(R.id.textView15).setVisibility(View.GONE);
        getView().findViewById(R.id.textView16).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.textView17).setVisibility(View.VISIBLE);
//        getView().findViewById(R.id.textView18).setVisibility(View.GONE);
//        getView().findViewById(R.id.textView25).setVisibility(View.GONE);
        getView().findViewById(R.id.course_material).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.EventsList).setVisibility(View.VISIBLE);
//        getView().findViewById(R.id.button7).setVisibility(View.GONE);
//        getView().findViewById(R.id.button5).setVisibility(View.GONE);
//        getView().findViewById(R.id.button6).setVisibility(View.GONE);
//        getView().findViewById(R.id.button8).setVisibility(View.GONE);
        count++;

        Button button = getView().findViewById(R.id.hiddenbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count%2==0){
                    getView().findViewById(R.id.textView11).setVisibility(View.GONE);
                    getView().findViewById(R.id.editText6).setVisibility(View.GONE);
//                    getView().findViewById(R.id.textView9).setVisibility(View.GONE);
//                    getView().findViewById(R.id.textView8).setVisibility(View.GONE);
                    getView().findViewById(R.id.textView12).setVisibility(View.GONE);
                    getView().findViewById(R.id.editText7).setVisibility(View.GONE);
                    getView().findViewById(R.id.textView13).setVisibility(View.GONE);
                    getView().findViewById(R.id.editText8).setVisibility(View.GONE);
                    getView().findViewById(R.id.textView14).setVisibility(View.GONE);
//                    getView().findViewById(R.id.textView18).setVisibility(View.GONE);
//                    getView().findViewById(R.id.textView25).setVisibility(View.GONE);
                    getView().findViewById(R.id.textView15).setVisibility(View.GONE);
                    getView().findViewById(R.id.textView16).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.textView17).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.course_material).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.EventsList).setVisibility(View.VISIBLE);
//                    getView().findViewById(R.id.button7).setVisibility(View.GONE);
//                    getView().findViewById(R.id.button5).setVisibility(View.GONE);
//                    getView().findViewById(R.id.button6).setVisibility(View.GONE);
//                    getView().findViewById(R.id.button8).setVisibility(View.GONE);
                    count++;

                }
                else {
                    getView().findViewById(R.id.textView11).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.editText6).setVisibility(View.VISIBLE);
//                    getView().findViewById(R.id.textView9).setVisibility(View.VISIBLE);
//                    getView().findViewById(R.id.textView8).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.textView12).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.editText7).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.textView13).setVisibility(View.VISIBLE);
//                    getView().findViewById(R.id.textView18).setVisibility(View.VISIBLE);
//                    getView().findViewById(R.id.textView25).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.editText8).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.textView14).setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.textView15).setVisibility(View.VISIBLE);
                    count++;
                }
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Courses").child(CourseMainPageStudent.courseID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                Courses currentcourse = dataSnapshot.getValue(Courses.class);
//                if(currentcourse!=null)
//                {
                    textView.setText(CourseMainPageStudent.courseID);
                    fullname_tv.setText(dataSnapshot.child("fullname").getValue().toString());
                    description_tv.setText(dataSnapshot.child("description").getValue().toString());
                    syllabus_tv.setText(dataSnapshot.child("syllabus").getValue().toString());
                    marks_tv.setText(dataSnapshot.child("marksDistribution").getValue().toString());
                    time_slots.setText(dataSnapshot.child("timeSlots").getValue().toString());
//                    events = currentcourse.getEvents();
//                    Collections.reverse(events);
//                    ListView listView1 = getView().findViewById(R.id.EventsList);
//                    final CustomAdapter1 customAdapter1 = new CustomAdapter1(getActivity(),events);
//////                    final ThreadAdapter adapter = new ThreadAdapter(DiscussionThreads.this, threads);
//                    listView1.setAdapter(customAdapter1);
//
//                    materials = currentcourse.getCourseMaterial();
//                    Collections.reverse(materials);
//                    ListView coursematerials = getView().findViewById(R.id.course_material);
//                    final CustomAdapter customAdapter = new CustomAdapter(getActivity(),materials);
//////                    final ThreadAdapter adapter = new ThreadAdapter(DiscussionThreads.this, threads);
//                    coursematerials.setAdapter(customAdapter);
                }

//            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses").child(CourseMainPageStudent.courseID).child("Course Material");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                materials = new ArrayList<CourseMaterial>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    CourseMaterial material = messageSnapshot.getValue(CourseMaterial.class);
                    materials.add(material);
                    Log.v("Title", material.getTitle());

                }
                Collections.reverse(materials);
                ListView listView = getView().findViewById(R.id.course_material);
                final CustomAdapter customAdapter = new CustomAdapter(getActivity(),materials);
                listView.setAdapter(customAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // now we have  all the value that will be needed for
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        CourseMaterial item = customAdapter.getItem(position);
                        downloadFiles(getActivity(),item.getFileName(),DIRECTORY_DOWNLOADS,item.getURL());
                        // now send the key with the intent you are showing
                    }
                });

//                    final ThreadAdapter adapter = new ThreadAdapter(DiscussionThreads.this, threads);

//                Log.v("Size", String.valueOf(materials.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses").child(CourseMainPageStudent.courseID).child("Events");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events = new ArrayList<Event>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    Event event = messageSnapshot.getValue(Event.class);
                    events.add(event);
                    Log.v("Title", event.getTitle());
                }
                Collections.reverse(events);
                Log.v("Size", String.valueOf(events.size()));
                ListView listView1 = getView().findViewById(R.id.EventsList);
                final CustomAdapter1 customAdapter1 = new CustomAdapter1(getActivity(),events);
////                    final ThreadAdapter adapter = new ThreadAdapter(DiscussionThreads.this, threads);
                listView1.setAdapter(customAdapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.course_information_student, container, false);

//        final TextView textView = getView().findViewById(R.id.textView4);
//        final TextView fullname_tv = getView().findViewById(R.id.textView7);
//        final TextView description_tv  = getView().findViewById(R.id.editText6);
//        final TextView syllabus_tv = getView().findViewById(R.id.editText7);
//        final TextView marks_tv = getView().findViewById(R.id.editText8);
//        final TextView time_slots = getView().findViewById(R.id.textView15);


        return rootView;
    }
    class CustomAdapter1 extends ArrayAdapter<Event> {

        public CustomAdapter1(Context context, ArrayList<Event> threads) {
            super(context, 0, threads);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.course_list_events_prof,null);
            TextView TitleView = convertView.findViewById(R.id.textView19);
            TextView datecreationText = convertView.findViewById(R.id.textView10);
            TextView TypeText = convertView.findViewById(R.id.textView22);
            TextView DescriptionText = convertView.findViewById(R.id.textView20);
            TextView VenueText = convertView.findViewById(R.id.textView21);
            TextView DateeventText = convertView.findViewById(R.id.textView23);
            TextView TimeText = convertView.findViewById(R.id.textView24);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_list_events_prof, parent, false);
            }
            TitleView.setText(events.get(position).getTitle());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = events.get(position).getDateOfCreation();
            try {
                Date todayWithZeroTime = formatter.parse(formatter.format(date));
                datecreationText.setText("Created on " +  todayWithZeroTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TypeText.setText("Type:" +  events.get(position).getType());
            DescriptionText.setText("Description:\n" + events.get(position).getDescription());
            VenueText.setText("Venue: "+events.get(position).getVenue());
            DateeventText.setText("Date: " + events.get(position).getDateOfEvent());
            TimeText.setText("Time: " + events.get(position).getTimeOfEvent());
            return convertView;
        }
    }

    class CustomAdapter extends ArrayAdapter<CourseMaterial> {

        public CustomAdapter(Context context, ArrayList<CourseMaterial> threads) {
            super(context, 0, threads);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.course_list_main_page_prof,null);
            TextView TitleView = convertView.findViewById(R.id.textView19);
            TextView fileText = convertView.findViewById(R.id.textView20);
            TextView dateText = convertView.findViewById(R.id.textView21);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_list_main_page_prof, parent, false);
            }
            TitleView.setText(materials.get(position).getTitle());
            fileText.setText(materials.get(position).getFileName());
            dateText.setText(materials.get(position).getDate().toString());
            return convertView;
        }
    }

    public void downloadFiles(Context context, String Filename,String FileDestination, String url)
    {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, FileDestination, Filename);
        downloadManager.enqueue(request);

//
    }

}