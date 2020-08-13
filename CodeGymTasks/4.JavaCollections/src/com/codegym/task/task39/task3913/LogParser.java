package com.codegym.task.task39.task3913;

import com.codegym.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private Path logDir;

    public LogParser(Path logDir) {

        this.logDir = logDir;
    }

    private List<LogLine> parse() throws IOException, ParseException {

        List<LogLine> logLines = new ArrayList<>();
        String line;
        String[] words;
        String[] events;
        LogLine logLine;
        int counter;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        for (String filePath : this.logDir.toFile().list()) {

            if (!filePath.endsWith(".log")) {
                continue;
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.logDir.toString() + File.separator + filePath));
            while(bufferedReader.ready()) {

                line = bufferedReader.readLine();
                words = line.split("\t");
                logLine = new LogLine();
                counter = 0;

                logLine.ipAddress = words[counter++];
                logLine.name = words[counter++];
                logLine.date = simpleDateFormat.parse(words[counter++]);

                events = words[counter++].split(" ");
                for (Event event : Event.values()) {
                    if (event.toString().equals(events[0])) {
                        logLine.event = event;
                        break;
                    }
                }
                if (events.length == 2) {
                    logLine.taskNumber = Integer.parseInt(events[1]);
                }

                for (Status status : Status.values()) {
                    if (status.toString().equals(words[counter])) {
                        logLine.status = status;
                        break;
                    }
                }

                logLines.add(logLine);
            }
            bufferedReader.close();
        }
        return logLines;
    }

    private List<LogLine> filter(Date after, Date before) {

        List<LogLine> logLines = new ArrayList<>();
        try {
            logLines = parse();
        } catch (Exception e) {
        }

        List<LogLine> logs = new ArrayList<>();
        for (LogLine line : logLines) {
            if (after != null && line.date.getTime() > after.getTime()) {
                if (before != null && line.date.getTime() < before.getTime()) {
                    logs.add(line);
                } else if (before == null) {
                    logs.add(line);
                }
            } else if (after == null) {
                if (before != null && line.date.getTime() < before.getTime()) {
                    logs.add(line);
                } else if (before == null) {
                    logs.add(line);
                }
            }
        }
        return logs;
    }

    private Set<String> getUsersForEvent(Event event, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<String> users = new HashSet<>();
        for (LogLine line : lines) {
            if (line.event == event) {
                users.add(line.name);
            }
        }
        return users;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {

        Set<String> lines = getUniqueIPs(after, before);
        return lines.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {

        List<LogLine> lines = filter(after, before);
        Set<String> ipAddresses = new HashSet<>();
        for (LogLine line : lines) {
            ipAddresses.add(line.ipAddress);
        }
        return ipAddresses;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {

        List<LogLine> lines = filter(after, before);
        Set<String> ipAddresses = new HashSet<>();
        for (LogLine line : lines) {
            if (line.name.equals(user)) {
                ipAddresses.add(line.ipAddress);
            }
        }
        return ipAddresses;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<String> ipAddresses = new HashSet<>();
        for (LogLine line : lines) {
            if (line.event == event) {
                ipAddresses.add(line.ipAddress);
            }
        }
        return ipAddresses;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<String> ipAddresses = new HashSet<>();
        for (LogLine line : lines) {
            if (line.status == status) {
                ipAddresses.add(line.ipAddress);
            }
        }
        return ipAddresses;
    }

    @Override
    public Set<String> getAllUsers() {
        List<LogLine> lines = filter(null, null);
        Set<String> users = new HashSet<>();
        for (LogLine line : lines) {
            users.add(line.name);
        }
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<String> users = new HashSet<>();
        for (LogLine line : lines) {
            users.add(line.name);
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Event> events = new HashSet<>();
        for (LogLine line : lines) {
            if (line.name.equals(user)) {
                events.add(line.event);
            }
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<String> users = new HashSet<>();
        for (LogLine line : lines) {
            if (line.ipAddress.equals(ip)) {
                users.add(line.name);
            }
        }
        return users;
    }

    @Override
    public Set<String> getUsersWhoHaveLoggedIn(Date after, Date before) {
        return getUsersForEvent(Event.LOGIN, after, before);
    }

    @Override
    public Set<String> getUsersWhoHaveDownloadedPlugin(Date after, Date before) {
        return getUsersForEvent(Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public Set<String> getUsersWhoHaveSentMessages(Date after, Date before) {
        return getUsersForEvent(Event.SEND_MESSAGE, after, before);
    }

    @Override
    public Set<String> getUsersWhoHaveAttemptedTasks(Date after, Date before) {
        return getUsersForEvent(Event.ATTEMPT_TASK, after, before);
    }

    @Override
    public Set<String> getUsersWhoHaveAttemptedTasks(Date after, Date before, int task) {
        List<LogLine> lines = filter(after, before);
        Set<String> users = new HashSet<>();
        for (LogLine line : lines) {
            if (line.event == Event.ATTEMPT_TASK && line.taskNumber == task) {
                users.add(line.name);
            }
        }
        return users;
    }

    @Override
    public Set<String> getUsersWhoHaveCompletedTasks(Date after, Date before) {
        return getUsersForEvent(Event.COMPLETE_TASK, after, before);
    }

    @Override
    public Set<String> getUsersWhoHaveCompletedTasks(Date after, Date before, int task) {
        List<LogLine> lines = filter(after, before);
        Set<String> users = new HashSet<>();
        for (LogLine line : lines) {
            if (line.event == Event.COMPLETE_TASK && line.taskNumber == task) {
                users.add(line.name);
            }
        }
        return users;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Date> dates = new HashSet<>();
        for (LogLine line : lines) {
            if (line.event == event && line.name.equals(user)) {
                dates.add(line.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Date> dates = new HashSet<>();
        for (LogLine line : lines) {
            if (line.status == Status.FAILED) {
                dates.add(line.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorOccurred(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Date> dates = new HashSet<>();
        for (LogLine line : lines) {
            if (line.status == Status.ERROR) {
                dates.add(line.date);
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedInFirstTime(String user, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Date date = null;
        for (LogLine line : lines) {
            if (line.event == Event.LOGIN && line.name.equals(user)) {
                if (date == null) {
                    date = line.date;
                } else if (line.date.getTime() < date.getTime()) {
                    date = line.date;
                }
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserAttemptedTask(String user, int task, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Date date = null;
        for (LogLine line : lines) {
            if (line.event == Event.ATTEMPT_TASK && line.name.equals(user) && line.taskNumber ==  task) {
                if (date == null) {
                    date = line.date;
                } else if (line.date.getTime() < date.getTime()) {
                    date = line.date;
                }
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserCompletedTask(String user, int task, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Date date = null;
        for (LogLine line : lines) {
            if (line.event == Event.COMPLETE_TASK && line.name.equals(user) && line.taskNumber == task) {
                if (date == null) {
                    date = line.date;
                } else if (line.date.getTime() < date.getTime()) {
                    date = line.date;
                }
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserSentMessages(String user, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Date> dates = new HashSet<>();
        for (LogLine line : lines) {
            if (line.event == Event.SEND_MESSAGE && line.name.equals(user)) {
                dates.add(line.date);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Date> dates = new HashSet<>();
        for (LogLine line : lines) {
            if (line.event == Event.DOWNLOAD_PLUGIN && line.name.equals(user)) {
                dates.add(line.date);
            }
        }
        return dates;
    }

    @Override
    public int getNumberOfEvents(Date after, Date before) {
        Set<Event> events = getAllEvents(after, before);
        return events.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Event> events = new HashSet<>();
        for (LogLine line : lines) {
            events.add(line.event);
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Event> events = new HashSet<>();
        for (LogLine line : lines) {
            if (line.ipAddress.equals(ip)) {
                events.add(line.event);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Event> events = new HashSet<>();
        for (LogLine line : lines) {
            if (line.name.equals(user)) {
                events.add(line.event);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Event> events = new HashSet<>();
        for (LogLine line : lines) {
            if (line.status == Status.FAILED) {
                events.add(line.event);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Set<Event> events = new HashSet<>();
        for (LogLine line : lines) {
            if (line.status == Status.ERROR) {
                events.add(line.event);
            }
        }
        return events;
    }

    @Override
    public int getNumberOfAttemptsToCompleteTask(int task, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        int attempts = 0;
        for (LogLine line : lines) {
            if (line.event == Event.ATTEMPT_TASK && line.taskNumber == task) {
                attempts++;
            }
        }
        return attempts;
    }

    @Override
    public int getNumberOfSuccessfulAttemptsToCompleteTask(int task, Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        int attempts = 0;
        for (LogLine line : lines) {
            if (line.event == Event.COMPLETE_TASK && line.taskNumber == task) {
                attempts++;
            }
        }
        return attempts;
    }

    @Override
    public Map<Integer, Integer> getAllAttemptedTasksAndNumberOfAttempts(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Map<Integer, Integer> events = new HashMap<>();
        for (LogLine line : lines) {
            if (line.event == Event.ATTEMPT_TASK) {
                if (!events.containsKey(line.taskNumber)) {
                    events.put(line.taskNumber, 0);
                }
                events.put(line.taskNumber, events.get(line.taskNumber) + 1);
            }
        }
        return events;
    }

    @Override
    public Map<Integer, Integer> getAllCompletedTasksAndNumberOfCompletions(Date after, Date before) {
        List<LogLine> lines = filter(after, before);
        Map<Integer, Integer> events = new HashMap<>();
        for (LogLine line : lines) {
            if (line.event == Event.COMPLETE_TASK) {
                if (!events.containsKey(line.taskNumber)) {
                    events.put(line.taskNumber, 0);
                }
                events.put(line.taskNumber, events.get(line.taskNumber) + 1);
            }
        }
        return events;
    }

    private Set<Date> getAllDates() {

        List<LogLine> lines = filter(null, null);
        Set<Date> dates = new HashSet<>();
        for (LogLine line : lines) {
            dates.add(line.date);
        }
        return dates;
    }

    private Set<Status> getAllStatuses() {

        List<LogLine> lines = filter(null, null);
        Set<Status> statuses = new HashSet<>();
        for (LogLine line : lines) {
            statuses.add(line.status);
        }
        return statuses;
    }

    private boolean match(String field, String value, LogLine line) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        switch (field) {
            case "ip":
                return line.ipAddress.equals(value);
            case "user":
                return line.name.equals(value);
            case "date":
                try {
                    Date date = simpleDateFormat.parse(value);
                    return date.getTime() == line.date.getTime();
                } catch (ParseException e) {
                    return false;
                }
            case "event":
                for (Event event : Event.values()) {
                    if (event.toString().equals(value)) {
                        return line.event == event;
                    }
                }
                return false;
            case "status":
                for (Status status : Status.values()) {
                    if (status.toString().equals(value)) {
                        return line.status == status;
                    }
                }
                return false;
            default:
                return false;
        }
    }

    private Object get(String field, LogLine line) {

        switch (field) {
            case "ip":
                return line.ipAddress;
            case "user":
                return line.name;
            case "date":
                return line.date;
            case "event":
                return line.event;
            case "status":
                return line.status;
            default:
                return null;
        }
    }

    @Override
    public Set<Object> execute(String query) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        if (query.equals("get ip")) {
            return getUniqueIPs(null, null).stream().collect(Collectors.toSet());
        } else if (query.equals("get user")) {
            return getAllUsers().stream().collect(Collectors.toSet());
        } else if (query.equals("get date")) {
            return getAllDates().stream().collect(Collectors.toSet());
        } else if (query.equals("get event")) {
            return getAllEvents(null, null).stream().collect(Collectors.toSet());
        } else if (query.equals("get status")) {
            return getAllStatuses().stream().collect(Collectors.toSet());
        } else {

            String range[] = query.split("and date between");
            Date after = null, before = null;
            if (range.length == 2) {
                String dates[] = range[1].split("and");
                try {
                    after = simpleDateFormat.parse(dates[0].replace("\"", "").trim());
                    before = simpleDateFormat.parse(dates[1].replace("\"", "").trim());
                } catch (ParseException e) {
                }
            }

            String[] values = range[0].split("=");
            String value = values[1].trim().replace("\"", "");

            String[] fields = values[0].split("for");
            String field1 = fields[0].replace("get", "").trim();
            String field2 = fields[1].trim();

            List<LogLine> lines = filter(after, before);
            Set<Object> result = new HashSet<>();
            for (LogLine line : lines) {
                if (match(field2, value, line)) {
                    result.add(get(field1, line));
                }
            }
            return result;
        }
    }

    public static class LogLine {

        String ipAddress;
        String name;
        Date date;
        Event event;
        int taskNumber;
        Status status;
    }
}