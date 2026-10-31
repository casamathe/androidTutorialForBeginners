package com.example.crochetapp;

import java.util.Arrays;
import java.util.List;

public class StitchData {

    public static List<Stitch> all() {
        return Arrays.asList(
            new Stitch("ch", "Chain", "Beginner",
                "Yarn over, then pull the yarn through the loop on your hook. Repeat for the length you need."),
            new Stitch("sl st", "Slip stitch", "Beginner",
                "Insert the hook into the stitch, yarn over, and pull through both the stitch and the loop on your hook."),
            new Stitch("sc", "Single crochet", "Beginner",
                "Insert the hook into the stitch, yarn over and pull up a loop (2 loops on hook). Yarn over and pull through both loops."),
            new Stitch("hdc", "Half double crochet", "Beginner",
                "Yarn over, insert the hook into the stitch, yarn over and pull up a loop (3 loops on hook). Yarn over and pull through all 3 loops."),
            new Stitch("dc", "Double crochet", "Beginner",
                "Yarn over, insert the hook into the stitch, yarn over and pull up a loop (3 loops). Yarn over, pull through 2 loops, yarn over again and pull through the last 2 loops."),
            new Stitch("tr", "Treble crochet", "Intermediate",
                "Yarn over twice, insert the hook into the stitch, yarn over and pull up a loop (4 loops). Yarn over and pull through 2 loops, three times."),
            new Stitch("inc", "Increase", "Beginner",
                "Work 2 stitches into the same stitch of the previous row."),
            new Stitch("dec", "Decrease", "Beginner",
                "Work 2 stitches together: start each stitch but leave the last loop on the hook, then yarn over and pull through all loops."),
            new Stitch("mr", "Magic ring", "Intermediate",
                "Wrap the yarn around two fingers to form a loop, insert the hook, pull up a loop and chain 1. Work your first round into the ring, then pull the tail tight."),
            new Stitch("FLO", "Front loop only", "Beginner",
                "Insert the hook under only the front loop of the stitch instead of both loops."),
            new Stitch("BLO", "Back loop only", "Beginner",
                "Insert the hook under only the back loop of the stitch instead of both loops."),
            new Stitch("pc", "Popcorn stitch", "Intermediate",
                "Work 4-5 double crochets into the same stitch, remove the hook, insert it into the first dc and the dropped loop, and pull through.")
        );
    }
}
